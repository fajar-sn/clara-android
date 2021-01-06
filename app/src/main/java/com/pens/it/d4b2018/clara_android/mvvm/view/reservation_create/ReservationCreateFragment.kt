package com.pens.it.d4b2018.clara_android.mvvm.view.reservation_create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.pens.it.d4b2018.clara_android.databinding.FragmentLoginBinding
import com.pens.it.d4b2018.clara_android.databinding.FragmentReservationCreateBinding
import com.pens.it.d4b2018.clara_android.databinding.ListFragmentBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.Resource
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.ReservationsRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.authentication.AuthViewModel
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import com.pens.it.d4b2018.clara_android.mvvm.view.main.MainActivity
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.enable
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.handleApiError
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.startNewActivity
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.visible
import kotlinx.coroutines.launch

class ReservationCreateFragment : BaseFragment<ReservationCreateViewModel, FragmentReservationCreateBinding, AssetsRepository>(){

    private var _binding: ListFragmentBinding? = null
    private val thisBinding
        get() = _binding!!


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val title = "Assets"



    }

    override fun getViewModel(): Class<ReservationCreateViewModel> {
        TODO("Not yet implemented")
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentReservationCreateBinding {
        TODO("Not yet implemented")
    }

    override fun getFragmentRepository(): AssetsRepository {
        TODO("Not yet implemented")
    }
}