package com.android.personal_project_retrofit.domain.repository

import androidx.lifecycle.LiveData
import com.android.personal_project_retrofit.domain.model.KakaoEntity
import com.android.personal_project_retrofit.presentation.search.Kakao

interface SearchRepository {
    suspend fun getSearchImage(query: String) : KakaoEntity
    fun modifyKakaoItem(item: Kakao?, list: LiveData<List<Kakao>>) : List<Kakao>
    fun removeKakaoItems() : List<Kakao>
}