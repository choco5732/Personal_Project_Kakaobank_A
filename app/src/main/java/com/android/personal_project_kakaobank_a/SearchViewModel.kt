package com.android.personal_project_kakaobank_a

import android.graphics.Insets.add
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_kakaobank_a.data.KakaoData
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel : ViewModel() {

    private val _list: MutableLiveData<List<KakaoData>> = MutableLiveData()
    val list: LiveData<List<KakaoData>> get() = _list

    private val idGenerate = AtomicLong(1L)

    init {
        _list.value = arrayListOf<KakaoData>()
    }

    fun addSearchItem(item: KakaoData?) {
        if (item == null) {
            return
        }
        val currentList = list.value!!.toMutableList()
        currentList?.add(item.copy(
            id = idGenerate.getAndIncrement()
        ))
        _list.value = currentList
    }


}
