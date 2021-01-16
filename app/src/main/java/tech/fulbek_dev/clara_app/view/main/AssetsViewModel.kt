package tech.fulbek_dev.clara_app.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import tech.fulbek_dev.clara_app.data.models.Asset
import tech.fulbek_dev.clara_app.data.models.AssetsResponse
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.AssetsRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

class AssetsViewModel(
        private val repository: AssetsRepository
): BaseViewModel(repository) {

    private val _assets: MutableLiveData<Resource<List<Asset>>> = MutableLiveData()
    val allAssets: LiveData<Resource<List<Asset>>>
        get() = _assets

    private val defaultQuery: String? = null
    private val currentQuery = MutableLiveData(defaultQuery)

    val assets = currentQuery.switchMap { queryString ->
        repository.getAssets(queryString).cachedIn(viewModelScope)
    }

    fun searchAssets(search: String) {
        currentQuery.value = search
    }

    fun getAssets() = viewModelScope.launch {
        _assets.value = Resource.Loading
        _assets.value = repository.getAssets()
    }

}