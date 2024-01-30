package com.android.personal_project_retrofit.domain.usecase

import com.android.personal_project_retrofit.domain.repository.SearchRepository

class GetSearchImageUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ) = repository.getSearchImage(
        query,
        sort,
        page,
        size
    )
}