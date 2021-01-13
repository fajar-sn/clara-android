package tech.fulbek_dev.clara_app.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tech.fulbek_dev.clara_app.data.repositories.*
import tech.fulbek_dev.clara_app.view.authentication.AuthViewModel
import tech.fulbek_dev.clara_app.view.main.AssetsViewModel
import tech.fulbek_dev.clara_app.view.main.ReservationsViewModel
import tech.fulbek_dev.clara_app.view.main.UserViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
        private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(AssetsViewModel::class.java) -> AssetsViewModel(repository as AssetsRepository) as T
            modelClass.isAssignableFrom(ReservationsViewModel::class.java) -> ReservationsViewModel(repository as ReservationsRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}