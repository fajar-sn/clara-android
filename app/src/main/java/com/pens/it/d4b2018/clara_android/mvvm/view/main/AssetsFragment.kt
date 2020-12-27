package com.pens.it.d4b2018.clara_android.mvvm.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.pens.it.d4b2018.clara_android.databinding.ListFragmentBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.AssetsListAdapter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AssetsFragment : BaseFragment<AssetsViewModel, ListFragmentBinding, AssetsRepository>() {

    private var _binding: ListFragmentBinding? = null
    private val thisBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "Assets"
        binding.titleTextView.text = title

        _binding = ListFragmentBinding.bind(view)

        val adapter = AssetsListAdapter()

        thisBinding.apply {
            listRecyclerview.layoutManager = GridLayoutManager(context, 2)
            listRecyclerview.setHasFixedSize(true)
            listRecyclerview.adapter = adapter
        }

        viewModel.assets.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun getViewModel() = AssetsViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = ListFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): AssetsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return AssetsRepository(api)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): AssetsFragment = AssetsFragment()
    }

}