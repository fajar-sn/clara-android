package com.pens.it.d4b2018.clara_android.mvvm.view.base

import androidx.lifecycle.ViewModel
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
        private val repository: BaseRepository
): ViewModel() {

    suspend fun logout(api: APIService) = withContext(Dispatchers.IO) { repository.logout(api) }

}