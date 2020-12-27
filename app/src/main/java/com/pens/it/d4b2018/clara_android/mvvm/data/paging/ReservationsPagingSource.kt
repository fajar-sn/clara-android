package com.pens.it.d4b2018.clara_android.mvvm.data.paging

import androidx.paging.PagingSource
import com.pens.it.d4b2018.clara_android.mvvm.data.models.Asset
import com.pens.it.d4b2018.clara_android.mvvm.data.models.Reservation
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import retrofit2.HttpException
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