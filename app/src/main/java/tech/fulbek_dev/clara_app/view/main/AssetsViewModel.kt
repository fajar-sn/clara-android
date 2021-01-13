package tech.fulbek_dev.clara_app.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import tech.fulbek_dev.clara_app.data.repositories.AssetsRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

class AssetsViewModel(
        private val repository: AssetsRepository
): BaseViewModel(repository) {

    private val defaultQuery: String? = null
    private val currentQuery = MutableLiveData(defaultQuery)

    val assets = currentQuery.switchMap { queryString ->
        repository.getAssets(queryString).cachedIn(viewModelScope)
    }

    fun searchAssets(search: String) {
        currentQuery.value = search
    }

}