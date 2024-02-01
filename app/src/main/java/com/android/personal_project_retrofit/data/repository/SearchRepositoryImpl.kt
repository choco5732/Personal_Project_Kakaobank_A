package com.android.personal_project_retrofit.data.repository

import com.android.personal_project_retrofit.data.remote.SearchRemoteDataSource
import com.android.personal_project_retrofit.domain.model.KakaoEntity
import com.android.personal_project_retrofit.domain.model.toKakaoEntity
import com.android.personal_project_retrofit.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource
): SearchRepository {
    override suspend fun getSearchImage(
        query: String
    ): KakaoEntity =
        remoteDataSource.getSearchImage(
            hashMapOf(
                "query" to query,
                "size" to "80"
            )
        ).toKakaoEntity()
}