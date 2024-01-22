package com.android.personal_project_kakaobank_a.presentation.library

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.model.KakaoModel
import com.android.personal_project_kakaobank_a.databinding.LibraryItemBinding
import com.bumptech.glide.Glide

class LibraryAdapter(
    private val onItemClick: (Int, KakaoModel) -> Unit
) : ListAdapter<KakaoModel, LibraryAdapter.ViewHolder>(

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
            LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: LibraryItemBinding,
        private val onItemClick: (Int, KakaoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoModel) = with(binding) {

            Glide.with(root)
                .load(item.thumbnail_url)
                .into(thumbnail)

            siteName.text = item.displaySiteName
            dateTime.text = item.dateTime

            container.setOnClickListener {
                item.isAdd = false
                onItemClick(
                    adapterPosition,
                    item
                )
                Log.d("LibraryAdapter", "#choco5732 라이브러리서 눌렀을시 isAdd 값은? : ${item.isAdd}")
            }
        }
    }
}
