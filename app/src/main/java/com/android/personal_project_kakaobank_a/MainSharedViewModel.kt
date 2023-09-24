package com.android.personal_project_kakaobank_a

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.personal_project_kakaobank_a.data.KakaoModel

class MainSharedViewModel : ViewModel() {

    private val _libraryEvent: MutableLiveData<MainSharedEventForLibrary> = MutableLiveData()
    val libraryEvent: LiveData<MainSharedEventForLibrary> get() = _libraryEvent
    fun updateLibraryItems(items: List<KakaoModel>?) {
        items?.filter {
            it.isAdd
        }?.also {
            _libraryEvent.value = MainSharedEventForLibrary.UpdateLibraryItems(it)
        }
    }
}

sealed interface MainSharedEventForLibrary {
    data class UpdateLibraryItems(
        val items: List<KakaoModel>
    ) : MainSharedEventForLibrary
}