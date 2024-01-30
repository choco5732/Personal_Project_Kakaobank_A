package com.android.personal_project_retrofit.data.remote

import com.android.personal_project_retrofit.data.model.KakaoResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchRemoteDataSource {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @QueryMap param: HashMap<String, String>
    ): KakaoResponse
}

//interface SearchRemoteDataSource {
//    @GET("v2/search/image")
//    suspend fun getSearchImage(
//        @Query("query") query: String,
//        @Query("sort") sort: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): KakaoResponse
//}
