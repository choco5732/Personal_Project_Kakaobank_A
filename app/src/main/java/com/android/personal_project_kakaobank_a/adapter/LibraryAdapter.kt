package com.android.personal_project_kakaobank_a.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.personal_project_kakaobank_a.data.KakaoData
import com.android.personal_project_kakaobank_a.databinding.LibraryItemBinding
import com.bumptech.glide.Glide

class LibraryAdapter(
    val list: MutableList<KakaoData>
) : RecyclerView.Adapter<LibraryAdapter.ViewHolder>() {

    fun addItems(itemList: List<KakaoData>?){
        if (itemList == null) {
            return
        }

        list.addAll(itemList)
//        notifyItemChanged(list.size - 1) 성능향상을 위해 사용할려 했으나.. 검색을 여러번 했을 시 에러가 발생해 주석..
        notifyDataSetChanged()
    }
    fun addItem(item: KakaoData?) {
        if (item == null) {
            return
        }
        list.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LibraryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private val binding: LibraryItemBinding
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