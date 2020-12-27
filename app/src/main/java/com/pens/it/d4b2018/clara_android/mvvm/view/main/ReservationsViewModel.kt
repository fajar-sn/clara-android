package com.pens.it.d4b2018.clara_android.mvvm.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.ReservationsRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseViewModel

class ReservationsViewModel(
        private val repository: ReservationsRepository
): BaseViewModel(repository) {

    private val defaultQuery: String? = null
    private val currentQuery = MutableLiveData(defaultQuery)

    val reservations = currentQuery.switchMap { queryString ->
        repository.getReservations(queryString).cachedIn(viewModelScope)
    }

    fun searchReservations(search: String) {
        currentQuery.value = search
    }

}