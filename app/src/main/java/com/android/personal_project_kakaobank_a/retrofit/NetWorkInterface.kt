package com.android.personal_project_kakaobank_a.retrofit

import com.android.personal_project_kakaobank_a.data.Kakao
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetWorkInterface {
    @GET("https://dapi.kakao.com/v2/search/image")
    suspend fun getKakao(@QueryMap param: HashMap<String, String>): Kakao
}