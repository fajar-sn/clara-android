package tech.fulbek_dev.clara_app.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import tech.fulbek_dev.clara_app.data.repositories.ReservationsRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

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