package com.android.personal_project_retrofit.domain.model

import com.google.gson.annotations.SerializedName


data class KakaoEntity(
    @SerializedName("documents")
    val documents: List<DocumentEntity>?,
    @SerializedName("meta")
    val meta: MetaEntity?
)
data class DocumentEntity(
    @SerializedName("collection")
    val collection: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("width")
    val width: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("display_sitename")
    val displaySitename: String?,
    @SerializedName("doc_url")
    val docUrl: String?,
    @SerializedName("datetime")
    val datetime: String?,
)

data class MetaEntity(
    @SerializedName("is_end")
    val isEnd: Boolean?,
    @SerializedName("pageable_count")
    val pageableCount: Int?,
    @SerializedName("total_count")
    val totalCount: Int?
)

