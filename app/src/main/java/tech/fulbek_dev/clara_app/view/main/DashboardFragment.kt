package tech.fulbek_dev.clara_app.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import tech.fulbek_dev.clara_app.databinding.FragmentDashboardBinding
import tech.fulbek_dev.clara_app.data.repositories.ReservationsRepository
import tech.fulbek_dev.clara_app.view.base.BaseFragment
import tech.fulbek_dev.clara_app.view.utils.ReservationsListAdapter
import tech.fulbek_dev.clara_app.view.utils.ReservationsLoadStateAdapter
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