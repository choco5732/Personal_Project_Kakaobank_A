package com.android.personal_project_retrofit.retrofit

import com.android.personal_project_retrofit.data.remote.SearchRemoteDataSource
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val KAKAO_BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val kakaoRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val search: SearchRemoteDataSource by lazy {
        kakaoRetrofit.create(SearchRemoteDataSource::class.java)
    }
}