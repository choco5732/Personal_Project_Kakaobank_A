package com.android.personal_project_kakaobank_a.adapter

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.SearchItemBinding
import com.bumptech.glide.Glide
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SearchAdapter(
    val list: MutableList<KakaoData>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
interface ItemClick {
    fun onClick(view: View, position: Int)
}

var itemClick: ItemClick? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(
        private val binding: SearchItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoData) = with(binding) {

            Glide.with(root)
                .load(item.thumbnail_url)
                .into(thumbnail)

            siteName.text = item.displaySiteName
            dateTime.text = item.dateTime
            Log.d("SearchAdapter","#choco5732 : time ${item.dateTime}")

            icStar.isVisible = item.isAdd == true

            container.setOnClickListener {
                itemClick?.onClick(it, adapterPosition)
            }

        }
    }
}



//            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//            val pareseTime = LocalDateTime.parse(item.dateTime, formatter)
//            dateTime.text = pareseTime.format(formatter)
//            Log.d("SearchAdapter","#choco5732 : Formattime ${item.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}")
