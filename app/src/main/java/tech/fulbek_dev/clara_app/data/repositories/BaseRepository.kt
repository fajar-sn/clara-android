package tech.fulbek_dev.clara_app.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import tech.fulbek_dev.clara_app.data.remote.APIService
import tech.fulbek_dev.clara_app.data.remote.Resource

abstract class BaseRepository(
        protected open val api: APIService
) {

    suspend fun <T> safeApiCall(
            apiCall: suspend () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    } else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }

    suspend fun logout(api: APIService) = safeApiCall {
        api.logout()
    }

}