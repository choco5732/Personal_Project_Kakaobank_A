package com.android.personal_project_kakaobank_a.domain.model

import com.android.personal_project_kakaobank_a.data.model.DocumentResponse
import com.android.personal_project_kakaobank_a.data.model.KakaoResponse
import com.android.personal_project_kakaobank_a.data.model.MetaResponse

fun KakaoResponse.toKakaoEntity() = KakaoEntity(
    documents = documents?.map{
        it.toEntity()
    },
    meta = meta?.toEntity()
)

fun MetaResponse.toEntity() = MetaEntity(
    isEnd = isEnd,
    pageableCount = pageableCount,
    totalCount = totalCount
)

fun DocumentResponse.toEntity() = DocumentEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySitename = displaySitename,
    docUrl = docUrl,
    datetime = datetime
)