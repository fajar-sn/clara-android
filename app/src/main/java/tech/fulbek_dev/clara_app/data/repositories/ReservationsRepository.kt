package tech.fulbek_dev.clara_app.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import tech.fulbek_dev.clara_app.data.models.ReservationRequest
import tech.fulbek_dev.clara_app.data.paging.ReservationsPagingSource
import tech.fulbek_dev.clara_app.data.remote.APIService

class ReservationsRepository(
        override val api: APIService
): BaseRepository(api) {

    fun getReservations(search: String?) =
            Pager(
                    config = PagingConfig(
                            pageSize = 8,
                            maxSize = 40,
                            enablePlaceholders = false
                    ),
                    pagingSourceFactory = { ReservationsPagingSource(api, search) }
            ).liveData

    suspend fun createNewReservation(reservationRequest: ReservationRequest) = safeApiCall {
        api.createNewReservation(reservationRequest)
    }

}