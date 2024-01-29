package com.android.personal_project_retrofit.presentation.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_retrofit.presentation.search.Kakao
import java.util.concurrent.atomic.AtomicLong

class LibraryViewModel : ViewModel() {

    private val _list: MutableLiveData<List<Kakao>> = MutableLiveData()
    val list: LiveData<List<Kakao>> get() = _list

    private val idGenerate = AtomicLong(1L)


    fun updateLibraryItems(items: List<Kakao>) {
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