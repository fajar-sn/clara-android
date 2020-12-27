package com.pens.it.d4b2018.clara_android.mvvm.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.pens.it.d4b2018.clara_android.mvvm.data.paging.AssetsPagingSource
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService

class AssetsRepository(
        override val api: APIService
): BaseRepository(api) {

    fun getAssets(search: String?) =
        Pager(
                config = PagingConfig(
                        pageSize = 8,
                        maxSize = 40,
                        enablePlaceholders = false
                ),
                pagingSourceFactory = { AssetsPagingSource(api, search) }
        ).liveData

}