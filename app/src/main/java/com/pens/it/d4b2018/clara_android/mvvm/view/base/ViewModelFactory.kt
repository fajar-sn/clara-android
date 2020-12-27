package com.pens.it.d4b2018.clara_android.mvvm.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.BaseRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.authentication.AuthViewModel
import com.pens.it.d4b2018.clara_android.mvvm.view.main.AssetsViewModel
import com.pens.it.d4b2018.clara_android.mvvm.view.main.UserViewModel
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
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}