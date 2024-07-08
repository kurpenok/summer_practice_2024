package kurpenov.kuat.marvelapp.network.model

import com.squareup.moshi.Json


data class MarvelAPI(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    @Json(name="`data`")
    val data: Data,
    val etag: String,
    val status: String
)
