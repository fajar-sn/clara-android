package com.pens.it.d4b2018.clara_android.mvvm.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pens.it.d4b2018.clara_android.databinding.ListFragmentBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.AssetsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.ReservationsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class ReservationsFragment : BaseFragment<ReservationsViewModel, ListFragmentBinding, ReservationsRepository>() {

    private var _binding: ListFragmentBinding? = null
    private val thisBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "Reservations"
        binding.titleTextView.text = title

        _binding = ListFragmentBinding.bind(view)

        val adapter = ReservationsListAdapter()

        thisBinding.apply {
            listRecyclerview.layoutManager = LinearLayoutManager(context)
            listRecyclerview.setHasFixedSize(true)
            listRecyclerview.itemAnimator = null
            listRecyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = ReservationsLoadStateAdapter { adapter.retry() },
                    footer = ReservationsLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.reservations.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener { loadState ->
            thisBinding.apply {
                progressBar.visible(loadState.source.refresh is LoadState.Loading)
                listRecyclerview.visible(loadState.source.refresh is LoadState.NotLoading)
                buttonRetry.visible(loadState.source.refresh is LoadState.Error)
                textViewError.visible(loadState.source.refresh is LoadState.Error)

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        adapter.itemCount < 1) {
                    listRecyclerview.visible(false)
                    textViewEmpty.visible(true)
                } else {
                    textViewEmpty.visible(false)
                }
            }
        }

        setSearchView()

    }

    private fun setSearchView() {
        thisBinding.listSearchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    thisBinding.listRecyclerview.scrollToPosition(0)
                    viewModel.searchReservations(query)
                    binding.listSearchview.clearFocus()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })
    }

    override fun getViewModel() = ReservationsViewModel::class.java

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = ListFragmentBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ReservationsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return ReservationsRepository(api)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ReservationsFragment = ReservationsFragment()
    }

}