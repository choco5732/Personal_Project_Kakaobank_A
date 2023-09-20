package com.android.personal_project_kakaobank_a.data


import com.google.gson.annotations.SerializedName

data class KakaoVideo(
    @SerializedName("documents")
    val documents: List<Documents>,
    @SerializedName("meta")
    val meta: Meta
)

data class Documents(
    @SerializedName("author")
    val author: String,
    @SerializedName("datetime")
    val datetime: String,
    @SerializedName("play_time")
    val playTime: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

//data class Meta2(
//    @SerializedName("is_end")
//    val isEnd: Boolean,
//    @SerializedName("pageable_count")
//    val pageableCount: Int,
//    @SerializedName("total_count")
//    val totalCount: Int
//)