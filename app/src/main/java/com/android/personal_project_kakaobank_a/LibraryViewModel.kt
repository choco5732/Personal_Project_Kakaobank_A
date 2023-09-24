package com.android.personal_project_kakaobank_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_kakaobank_a.data.KakaoModel
import java.util.concurrent.atomic.AtomicLong

class LibraryViewModel : ViewModel() {

    private val _list: MutableLiveData<List<KakaoModel>> = MutableLiveData()
    val list: LiveData<List<KakaoModel>> get() = _list

    private val idGenerate = AtomicLong(1L)


    fun updateLibraryItems(items: List<KakaoModel>) {
        _list.value = items
    }

    fun removeLibraryItem(position: Int) {

        if (position < 0) {
            return
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList.removeAt(position)
        _list.value = currentList
    }
}