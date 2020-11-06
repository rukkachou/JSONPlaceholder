package com.rukka.jsonplaceholder.networks

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(
   val albumId: Int,
   val id: Int,
   val title: String,
   @Json(name = "url") val imageUrl: String,
   @Json(name = "thumbnailUrl") val thumbnailImageUrl: String
) : Parcelable