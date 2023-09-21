package com.android.personal_project_kakaobank_a.retrofit

import com.android.personal_project_kakaobank_a.BuildConfig
import com.android.personal_project_kakaobank_a.data.Kakao
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

/**
 *  Key값의 기밀성을 위해 local.properties에 숨겨뒀습니다. (Secrets Gradle Plugin 라이브러리 사용)
 *  만약 다른 컴퓨터에서 실행이 안된다면
 *  18번째 줄 @Header("Authorization") apiKey: String = "KakaoAK 8e566920812d0d734b7610e985e603ef" 로 수정해주세요!
 *  이 주석은 곧 삭제할 예정입니다!!
 */
interface NetWorkInterface {
    @GET("v2/search/image")
    suspend fun getKakao(
        @Header("Authorization") apiKey: String = BuildConfig.REST_API_KEY,
        @QueryMap param: HashMap<String, String>
    ): Kakao
}
