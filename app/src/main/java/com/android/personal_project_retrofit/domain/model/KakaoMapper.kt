package com.android.personal_project_retrofit.domain.model

import com.android.personal_project_retrofit.data.model.DocumentResponse
import com.android.personal_project_retrofit.data.model.KakaoResponse
import com.android.personal_project_retrofit.data.model.MetaResponse

// kotlin extention
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