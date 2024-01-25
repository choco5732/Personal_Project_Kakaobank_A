package com.android.personal_project_kakaobank_a.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kakao(
    val id: Long? = -1,
    val thumbnail_url: String?,
    val displaySiteName: String?,
    val dateTime: String?,
    var isAdd: Boolean
) : Parcelable
