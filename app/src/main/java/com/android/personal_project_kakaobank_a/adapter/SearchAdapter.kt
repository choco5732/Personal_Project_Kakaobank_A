package com.android.personal_project_kakaobank_a.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.SearchItemBinding
import com.bumptech.glide.Glide

class SearchAdapter(
) : ListAdapter<KakaoModel, SearchAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<KakaoModel>() {
        override fun areItemsTheSame(
            oldItem: KakaoModel,
            newItem: KakaoModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: KakaoModel,
            newItem: KakaoModel
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: SearchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoModel) = with(binding) {

            Glide.with(root)
                .load(item.thumbnail_url)
                .into(thumbnail)

            icCheck.isVisible = item.isAdd == true

            siteName.text = item.displaySiteName
            dateTime.text = item.dateTime

            container.setOnClickListener {
                itemClick?.onClick(it, adapterPosition)
            }
        }
    }
}