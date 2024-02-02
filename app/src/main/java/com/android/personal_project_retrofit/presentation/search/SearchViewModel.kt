package com.android.personal_project_retrofit.presentation.search

import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.personal_project_retrofit.data.repository.SearchRepositoryImpl
import com.android.personal_project_retrofit.domain.repository.SearchRepository
import com.android.personal_project_retrofit.domain.usecase.GetSearchImageUseCase
import com.android.personal_project_retrofit.retrofit.RetrofitClient
import com.android.personal_project_retrofit.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

class SearchViewModel(
    private val idGenerate: AtomicLong,
    private val searchImage: GetSearchImageUseCase,
    private val repository: SearchRepository
) : ViewModel() {

    private val _list: MutableLiveData<List<Kakao>> = MutableLiveData()
    val list: LiveData<List<Kakao>> get() = _list

    private val _event: MutableLiveData<SearchEvent> = SingleLiveEvent()
    val event: LiveData<SearchEvent> get() = _event

    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                val image = searchImage(query)
                val currentList = list.value.orEmpty().toMutableList()

                    val list = image.documents?.map {
                        Kakao(
                            id = idGenerate.getAndIncrement(),
                            thumbnail_url = it.thumbnailUrl,
                            displaySiteName = it.displaySitename,
                            dateTime = it.datetime,
                            isAdd = false
                        )
                    }.orEmpty()

                currentList.addAll(list)
                _list.value = currentList

            }.onFailure {
                // network error
                Log.e("choco5732", it.message.toString())
            }
        }
    }

    fun modifyKakaoItem(
        item: Kakao?,
        list: LiveData<List<Kakao>>
    ) {
        _list.value = repository.modifyKakaoItem(item, list)
    }

    fun removeKakaoItems() {
        _list.value = repository.removeKakaoItems()
    }

    fun loadData(
        type: String,
        name: String
    ) {
        _event.value = SearchEvent.LoadData(
            type,
            name
        )
    }

    fun saveData(
        type: String,
        name: String
    ) {
        _event.value = SearchEvent.SaveData(
            type,
            name
        )
    }

}

