package com.android.personal_project_kakaobank_a.retrofit

import com.android.personal_project_kakaobank_a.BuildConfig
import com.android.personal_project_kakaobank_a.data.model.Kakao
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("v2/search/image")
    suspend fun getKakao(
        @Header("Authorization") apiKey: String = BuildConfig.REST_API_KEY,
        @QueryMap param: HashMap<String, String>
    ): Kakao

}
