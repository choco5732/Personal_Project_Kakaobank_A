package com.android.personal_project_retrofit.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.personal_project_retrofit.data.repository.SearchRepositoryImpl
import com.android.personal_project_retrofit.domain.repository.SearchRepository
import com.android.personal_project_retrofit.domain.usecase.GetSearchImageUseCase
import com.android.personal_project_retrofit.retrofit.RetrofitClient
import java.util.concurrent.atomic.AtomicLong

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