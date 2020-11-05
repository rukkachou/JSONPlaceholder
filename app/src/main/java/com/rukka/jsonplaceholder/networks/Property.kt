package com.rukka.jsonplaceholder.networks

import com.squareup.moshi.Json

data class Property(
   val albumId: Int,
   val id: Int,
   val title: String,
   @Json(name = "url") val imageUrl: String,
   @Json(name = "thumbnailUrl") val thumbnailImageUrl: String
)