package com.pens.it.d4b2018.clara_android.mvvm.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseViewModel

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