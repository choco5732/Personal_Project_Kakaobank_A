package com.android.personal_project_retrofit.data.remote

import com.android.personal_project_retrofit.data.model.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchRemoteDataSource {
    @GET("v2/search/image")
    suspend fun getKakao(
        @QueryMap param: HashMap<String, String>
    ): KakaoResponse
}
