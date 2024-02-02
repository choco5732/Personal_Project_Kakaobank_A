package com.android.personal_project_retrofit.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.personal_project_retrofit.data.remote.SearchRemoteDataSource
import com.android.personal_project_retrofit.domain.model.KakaoEntity
import com.android.personal_project_retrofit.domain.model.toKakaoEntity
import com.android.personal_project_retrofit.domain.repository.SearchRepository
import com.android.personal_project_retrofit.presentation.search.Kakao
import java.util.concurrent.atomic.AtomicLong

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDataSource
): SearchRepository {

    private val list = mutableListOf<Kakao>()
    override suspend fun getSearchImage(
        query: String
    ): KakaoEntity =
        remoteDataSource.getSearchImage(
            hashMapOf(
                "query" to query,
                "size" to "80"
            )
        ).toKakaoEntity()

    override fun modifyKakaoItem(
        item: Kakao?,
        list: LiveData<List<Kakao>>
    ): List<Kakao> {
        Log.d("choco5733", "item : $item")
        fun findIndex(item: Kakao?): Int {
            val currentList = list.value.orEmpty().toMutableList()
            val findKakao = currentList.find {
                it.id == item?.id
            }
            return currentList.indexOf(findKakao)
        }

        if (item == null) {
            return list.value.orEmpty().toMutableList()
        }

        val findPostion = findIndex(item)
        if (findPostion < 0) {
            Log.d("choco5733", "findPosition : $findPostion")
            return list.value.orEmpty().toMutableList()
        }

        val currentList = list.value.orEmpty().toMutableList()
        currentList[findPostion] = item
        return ArrayList<Kakao>(currentList)
    }

    override fun removeKakaoItems(): List<Kakao> {
        list.clear()
        return ArrayList<Kakao>(list)
    }
}