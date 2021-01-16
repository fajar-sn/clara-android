package tech.fulbek_dev.clara_app.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import tech.fulbek_dev.clara_app.data.models.ReservationRequest
import tech.fulbek_dev.clara_app.data.remote.Resource
import tech.fulbek_dev.clara_app.data.repositories.ReservationsRepository
import tech.fulbek_dev.clara_app.view.base.BaseViewModel

class ReservationsViewModel(
        private val repository: ReservationsRepository
): BaseViewModel(repository) {

    private val _reservationResponse: MutableLiveData<Resource<ResponseBody>> = MutableLiveData()
    val reservationResponse: LiveData<Resource<ResponseBody>>
        get() = _reservationResponse

    private val defaultQuery: String? = null
    private val currentQuery = MutableLiveData(defaultQuery)

    val reservations = currentQuery.switchMap { queryString ->
        repository.getReservations(queryString).cachedIn(viewModelScope)
    }

    fun searchReservations(search: String) {
        currentQuery.value = search
    }

    fun createNewReservation(reservationRequest: ReservationRequest) = viewModelScope.launch {
        _reservationResponse.value = Resource.Loading
        _reservationResponse.value = repository.createNewReservation(reservationRequest)
    }

}