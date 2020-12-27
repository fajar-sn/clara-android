package com.pens.it.d4b2018.clara_android.mvvm.view.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.pens.it.d4b2018.clara_android.R
import com.pens.it.d4b2018.clara_android.databinding.AssetCardBinding
import com.pens.it.d4b2018.clara_android.databinding.ReservationCardBinding
import com.pens.it.d4b2018.clara_android.mvvm.data.models.Asset
import com.pens.it.d4b2018.clara_android.mvvm.data.models.Reservation
import com.pens.it.d4b2018.clara_android.mvvm.data.remote.APIUtils

class ReservationsListAdapter : PagingDataAdapter<Reservation, ReservationsListAdapter.ReservationsViewHolder>(RESERVATIONS_COMPARATOR) {

    override fun onBindViewHolder(holder: ReservationsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null)
            holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationsViewHolder {
        val binding = ReservationCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReservationsViewHolder(binding)
    }

    class ReservationsViewHolder(private val binding: ReservationCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reservation: Reservation) {
            val photoUrl = APIUtils.BASE_URL + APIUtils.IMAGES_URL + reservation.asset.image
            val quantityString = reservation.asset.quantity.toString() + " units"
            Log.i("HEHE", "bind: " + reservation.asset.quantity.toString() + ", " + reservation.asset)

            binding.apply {
                Glide.with(itemView)
                        .load(photoUrl)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_baseline_error_outline_24)
                        .into(reservationImage)


                reservationStatusChip.text = reservation.status
                reservationTitleTextview.text = reservation.asset.name
                reservationDateTextview.text = reservation.begin // FIX LATER, should be date created instead of begin
                reservationAssetQtyTextview.text = quantityString
            }
        }

    }

    companion object {
        private val RESERVATIONS_COMPARATOR = object : DiffUtil.ItemCallback<Reservation>() {
            override fun areItemsTheSame(oldItem: Reservation, newItem: Reservation) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Reservation, newItem: Reservation) =
                    oldItem == newItem
        }
    }

}