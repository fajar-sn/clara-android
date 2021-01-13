package tech.fulbek_dev.clara_app.view.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import tech.fulbek_dev.clara_app.R
import tech.fulbek_dev.clara_app.databinding.AssetCardBinding
import tech.fulbek_dev.clara_app.data.models.Asset
import tech.fulbek_dev.clara_app.data.remote.APIUtils

class AssetsListAdapter : PagingDataAdapter<Asset, AssetsListAdapter.AssetsViewHolder>(ASSETS_COMPARATOR) {

    override fun onBindViewHolder(holder: AssetsViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null)
            holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetsViewHolder {
        val binding = AssetCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AssetsViewHolder(binding)
    }

    class AssetsViewHolder(private val binding: AssetCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(asset: Asset) {
            val photoUrl = APIUtils.BASE_URL + APIUtils.IMAGES_URL + asset.image
            val quantityString = asset.quantity.toString() + " units"
            val availableString = asset.available.toString() + " available"

            binding.apply {
                Glide.with(itemView)
                        .load(photoUrl)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_baseline_error_outline_24)
                        .into(assetImage)

                assetTitleTextview.text = asset.name
                assetQtyTextview.text = quantityString
                assetAvailableTextview.text = availableString
            }
        }

    }

    companion object {
        private val ASSETS_COMPARATOR = object : DiffUtil.ItemCallback<Asset>() {
            override fun areItemsTheSame(oldItem: Asset, newItem: Asset) =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Asset, newItem: Asset) =
                    oldItem == newItem
        }
    }

}