package tech.fulbek_dev.clara_app.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import tech.fulbek_dev.clara_app.databinding.ListFragmentBinding
import tech.fulbek_dev.clara_app.data.repositories.ReservationsRepository
import tech.fulbek_dev.clara_app.view.base.BaseFragment
import tech.fulbek_dev.clara_app.view.utils.ReservationsListAdapter
import tech.fulbek_dev.clara_app.view.utils.ReservationsLoadStateAdapter
import tech.fulbek_dev.clara_app.view.utils.visible
import kotlinx.coroutines.runBlocking
import tech.fulbek_dev.clara_app.view.new_reservation.NewReservationActivity
import tech.fulbek_dev.clara_app.view.utils.startNewActivity

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

            addFloatActionButton.setOnClickListener {
                requireActivity().startNewActivity(NewReservationActivity::class.java)
            }
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