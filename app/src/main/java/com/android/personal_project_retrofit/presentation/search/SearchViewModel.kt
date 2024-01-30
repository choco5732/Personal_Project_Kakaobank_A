package com.android.personal_project_retrofit.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.personal_project_retrofit.data.repository.SearchRepositoryImpl
import com.android.personal_project_retrofit.domain.repository.SearchRepository
import com.android.personal_project_retrofit.domain.usecase.GetSearchImageUseCase
import com.android.personal_project_retrofit.retrofit.RetrofitClient
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel(
    private val idGenerate: AtomicLong,
    private val searchImage: GetSearchImageUseCase
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

    fun search(query: String) {
        viewModelScope.launch {
            val list = searchImage(
                query = query,
                page = 80,
                sort = "accuracy",
                size = 1
            )
            Log.d("choco5732", list.toString())
        }
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
    private val repository: SearchRepository = SearchRepositoryImpl(
        RetrofitClient.search
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                idGenerate,
                GetSearchImageUseCase(repository)
            ) as T
        } else {
            throw IllegalArgumentException("Not found ViewModel class.")
        }
    }
}