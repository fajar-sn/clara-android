package tech.fulbek_dev.clara_app.data.paging

import androidx.paging.PagingSource
import retrofit2.HttpException
import tech.fulbek_dev.clara_app.data.models.Reservation
import tech.fulbek_dev.clara_app.data.remote.APIService
import java.io.IOException

private const val RESERVATIONS_STARTING_PAGE_INDEX = 1

class ReservationsPagingSource(
        private val api: APIService,
        private val search: String? = null
) : PagingSource<Int, Reservation>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Reservation> {
        val position = params.key ?: RESERVATIONS_STARTING_PAGE_INDEX

        return try {
            val response = api.getReservations(search, position)
            val reservations = response.reservations

            LoadResult.Page(
                    data = reservations,
                    prevKey = if (position == RESERVATIONS_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (position == response.lastPage) null else position + 1,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

}