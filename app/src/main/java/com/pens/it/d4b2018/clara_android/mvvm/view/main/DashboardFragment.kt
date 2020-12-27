package com.pens.it.d4b2018.clara_android.mvvm.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.pens.it.d4b2018.clara_android.databinding.FragmentDashboardBinding
import com.pens.it.d4b2018.clara_android.databinding.ListFragmentBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIService
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.ReservationsRepository
import com.pens.it.d4b2018.clara_android.mvvm.data.repositories.UserRepository
import com.pens.it.d4b2018.clara_android.mvvm.view.base.BaseFragment
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.ReservationsListAdapter
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.ReservationsLoadStateAdapter
import com.pens.it.d4b2018.clara_android.mvvm.view.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DashboardFragment : BaseFragment<ReservationsViewModel, FragmentDashboardBinding, ReservationsRepository>() {

    override fun getViewModel() = ReservationsViewModel::class.java

    private var _binding: FragmentDashboardBinding? = null
    private val thisBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDashboardBinding.bind(view)

        val adapter = ReservationsListAdapter()

        thisBinding.apply {
            dashboardReservationRecyclerView.layoutManager = LinearLayoutManager(context)
            dashboardReservationRecyclerView.setHasFixedSize(true)
            dashboardReservationRecyclerView.itemAnimator = null
            dashboardReservationRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = ReservationsLoadStateAdapter { adapter.retry() },
                    footer = ReservationsLoadStateAdapter { adapter.retry() }
            )
//            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.reservations.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

//        adapter.addLoadStateListener { loadState ->
//            thisBinding.apply {
//                progressBar.visible(loadState.source.refresh is LoadState.Loading)
//                listRecyclerview.visible(loadState.source.refresh is LoadState.NotLoading)
//                buttonRetry.visible(loadState.source.refresh is LoadState.Error)
//                textViewError.visible(loadState.source.refresh is LoadState.Error)
//
//                // empty view
//                if (loadState.source.refresh is LoadState.NotLoading &&
//                        loadState.append.endOfPaginationReached &&
//                        adapter.itemCount < 1) {
//                    listRecyclerview.visible(false)
//                    textViewEmpty.visible(true)
//                } else {
//                    textViewEmpty.visible(false)
//                }
//            }
//        }
    }

    override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentDashboardBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ReservationsRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = retrofitClient.buildApi(token)
        return ReservationsRepository(api)
    }

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

}