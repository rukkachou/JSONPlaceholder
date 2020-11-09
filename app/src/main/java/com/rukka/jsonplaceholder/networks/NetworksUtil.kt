package com.rukka.jsonplaceholder.networks

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

fun loadImageWithUrl(imageUrl: String?): Bitmap? {
    imageUrl?.let {
        val url = URL(imageUrl)
        try {
            val conn = url.openConnection()
            conn.setRequestProperty("User-agent", "your-user-agent")
            conn.connect()
            return BitmapFactory.decodeStream(conn.getInputStream())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return null
}

fun loadJsonDataWithUrl(urlString: String): Property {
    val sb = StringBuilder()
    val url = URL(urlString)
    val inputStream = url.openStream()
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    var lineString = bufferedReader.readLine()
    while (lineString != null) {
        sb.append(lineString)
        lineString = bufferedReader.readLine()
    }
    inputStream.close()
    if (sb.isNotEmpty()) {
        val jsonObject = JSONObject(sb.toString())
        return Property(
            jsonObject.getInt("albumId"),
            jsonObject.getInt("id"),
            jsonObject.getString("title"),
            jsonObject.getString("url"),
            jsonObject.getString("thumbnailUrl"))
    } else {
        throw NoSuchElementException()
    }
}
