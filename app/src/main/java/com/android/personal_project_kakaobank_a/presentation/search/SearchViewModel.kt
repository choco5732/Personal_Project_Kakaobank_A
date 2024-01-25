package com.android.personal_project_kakaobank_a.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel(
    private val idGenerate: AtomicLong
) : ViewModel() {

    private val _list: MutableLiveData<List<Kakao>> = MutableLiveData()
    val list: LiveData<List<Kakao>> get() = _list


    fun addSearchItem(item: Kakao?) {
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
        item: Kakao?
    ) {

        fun findIndex(item: Kakao?): Int {
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

class SearchViewModelFactory : ViewModelProvider.Factory {

    // id 를 부여할 값
    private val idGenerate = AtomicLong(1L)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(idGenerate) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}