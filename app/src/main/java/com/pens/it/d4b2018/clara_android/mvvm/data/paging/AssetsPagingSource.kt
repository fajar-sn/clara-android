package com.pens.it.d4b2018.clara_android.mvvm.data.paging

import androidx.paging.PagingSource
import com.pens.it.d4b2018.clara_android.mvvm.data.models.Asset
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import retrofit2.HttpException
import java.io.IOException

private const val ASSETS_STARTING_PAGE_INDEX = 1

class AssetsPagingSource(
        private val api: APIService,
        private val search: String? = null
) : PagingSource<Int, Asset>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Asset> {
        val position = params.key ?: ASSETS_STARTING_PAGE_INDEX

        return try {
            val response = api.getAssets(search, position)
            val assets = response.assets

            LoadResult.Page(
                    data = assets,
                    prevKey = if (position == ASSETS_STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (position == response.lastPage) null else position + 1,
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

}