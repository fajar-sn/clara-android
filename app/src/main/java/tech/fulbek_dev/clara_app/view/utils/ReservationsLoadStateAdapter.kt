package tech.fulbek_dev.clara_app.view.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.fulbek_dev.clara_app.databinding.LoadStateFooterBinding

class ReservationsLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<ReservationsLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: LoadStateFooterBinding) :
            RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.visible(loadState is LoadState.Loading)
                retryButton.visible(loadState !is LoadState.Loading)
                errorTextView.visible(loadState !is LoadState.Loading)
            }
        }

    }

}