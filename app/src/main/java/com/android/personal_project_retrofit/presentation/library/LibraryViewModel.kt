package com.android.personal_project_retrofit.presentation.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_retrofit.presentation.search.Kakao
import com.android.personal_project_retrofit.util.SingleLiveEvent
import java.util.concurrent.atomic.AtomicLong

class LibraryViewModel : ViewModel() {

    private val _list: MutableLiveData<List<Kakao>> = MutableLiveData()
    val list: LiveData<List<Kakao>> get() = _list

    private val _event: MutableLiveData<LibraryEvent> = SingleLiveEvent()
    val event: LiveData<LibraryEvent> get() = _event

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

    fun removeToast(
        text: String
    ) {
        _event.value = LibraryEvent.RemoveToast(
            text
        )
    }
}