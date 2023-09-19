package com.android.personal_project_kakaobank_a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.ImageItemBinding
import com.bumptech.glide.Glide

class ImageAdapter(
    val list: MutableList<KakaoData>,

) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ImageItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(
        private val binding: ImageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoData) = with(binding) {

            Glide.with(root)
                .load(item.thumbnail_url)
                .into(thumbnail)

            siteName.text = item.displaySiteName
            dateTime.text = item.dateTime

        }
    }
}