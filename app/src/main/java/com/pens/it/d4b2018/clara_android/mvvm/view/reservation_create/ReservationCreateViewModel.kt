package com.pens.it.d4b2018.clara_android.mvvm.view.reservation_create

import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AuthRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.ReservationsRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseViewModel

class ReservationCreateViewModel (
        private val repository: ReservationsRepository
) : BaseViewModel(repository)  {
}