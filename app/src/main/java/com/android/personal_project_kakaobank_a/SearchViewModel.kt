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

        currentList.add(
            item.copy(
                id = idGenerate.getAndIncrement()
            )
        )
        _list.value = currentList
    }

    fun modifyKakaoItem(
        item: KakaoModel?
    ) {

        fun findIndex(item: KakaoModel?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findKakao = currentList.find {
                it.id == item?.id
            }
            return currentList.indexOf(findKakao)
        }

        if (item == null) {
            return
        }

        val findPostion = findIndex(item)
        if (findPostion < 0) {
            return
        }


        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPostion] = item
        _list.value = currentList
    }

    fun removeKakaoItems() {
        val currentList = list.value.orEmpty().toMutableList()
        currentList.clear()
        _list.value = currentList
    }
}
