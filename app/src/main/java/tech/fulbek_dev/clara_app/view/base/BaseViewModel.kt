package tech.fulbek_dev.clara_app.view.base

import androidx.lifecycle.ViewModel
import tech.fulbek_dev.clara_app.data.remote.APIService
import tech.fulbek_dev.clara_app.data.repositories.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
        private val repository: BaseRepository
): ViewModel() {

    suspend fun logout(api: APIService) = withContext(Dispatchers.IO) { repository.logout(api) }

}