package com.android.personal_project_kakaobank_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_kakaobank_a.data.KakaoModel
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel : ViewModel() {

    private val _list: MutableLiveData<List<KakaoModel>> = MutableLiveData()
    val list: LiveData<List<KakaoModel>> get() = _list

    private val idGenerate = AtomicLong(1L)

    fun addSearchItem(item: KakaoModel?) {
        if (item == null) {
            return
        }
        val currentList = list.value.orEmpty().toMutableList()

        currentList.add(item.copy(
            id = idGenerate.getAndIncrement()
            )
        )
        _list.value = currentList
    }
}
