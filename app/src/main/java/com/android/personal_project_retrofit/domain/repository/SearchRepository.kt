package com.android.personal_project_retrofit.domain.repository

import com.android.personal_project_retrofit.domain.model.KakaoEntity

interface SearchRepository {
     suspend fun getSearchImage(
        query: String
    ) : KakaoEntity
}