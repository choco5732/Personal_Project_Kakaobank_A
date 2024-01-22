package com.android.personal_project_kakaobank_a.presentation.search

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.model.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.SearchItemBinding
import com.bumptech.glide.Glide

class SearchAdapter(
    private val onItemClick: (Int, KakaoModel) -> Unit
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: SearchItemBinding,
        private val onItemClick: (Int, KakaoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoModel) = with(binding) {

            Glide.with(root)
                .load(item.thumbnail_url)
                .into(thumbnail)

            siteName.text = item.displaySiteName
            dateTime.text = item.dateTime

            icCheck.isVisible = item.isAdd == true

//            if (item.isAdd) {
//                icCheck.isVisible = true
//            } else {
//                icCheck.isVisible = false
//            }

            container.setOnClickListener {
                item.isAdd = !item.isAdd
                Log.d("SearchAdapter", "#choco5732 눌렀을시 isAdd 테스트 : ${item.isAdd}")
                if (item.isAdd) {
                    icCheck.isVisible = true
                    onItemClick(
                        adapterPosition,
                        item
                    )
                } else {
                    icCheck.isVisible = false
                }
            }
        }
    }
}