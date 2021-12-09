package id.bagas.aditia.marsphotos.network

import com.squareup.moshi.Json

data class MarsPhoto (
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String
    //val id: String, val img_src: String

)