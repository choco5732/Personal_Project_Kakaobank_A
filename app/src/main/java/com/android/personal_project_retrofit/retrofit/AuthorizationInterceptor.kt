package com.android.personal_project_retrofit.retrofit

import com.android.personal_project_retrofit.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK 8e566920812d0d734b7610e985e603ef"
            ).build()
        return chain.proceed(newRequest)
    }
}