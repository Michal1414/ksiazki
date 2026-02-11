package com.example.books

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

object BookInformation {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) { json() }
    }
    suspend fun getWorkKeyFromTitle(title: String): String? = withContext(Dispatchers.IO) {
        val url = "https://openlibrary.org/search.json?q=$title"
        val response: String = client.get(url).body()

        val jsonObject = JSONObject(response)

        val docsArray = jsonObject.optJSONArray("docs")
        if (docsArray != null && docsArray.length() > 0) {
            val firstDoc = docsArray.getJSONObject(0)
            val key = firstDoc.optString("key", null)
            println("Klucz: $key")
            return@withContext key
        }

        return@withContext null
    }

    suspend fun getTitleFromWKey(wKey: String?): String? = withContext(Dispatchers.IO) {
        if (wKey == null) return@withContext null

        val url = "https://openlibrary.org/$wKey.json"
        val body: String = client.get(url).body()

        val jsonObject = JSONObject(body)

        val title = jsonObject.optString("title", null)

        println(title)
        return@withContext title
    }


    suspend fun getDescriptionFromWKey(wKey: String?): String? = withContext(Dispatchers.IO) {
        if (wKey == null) return@withContext null

        val url = "https://openlibrary.org/$wKey.json"
        val body: String = client.get(url).body()

        val jsonObject = JSONObject(body)

        val description = jsonObject.opt("description")

        val result = when (description) {
            is JSONObject -> description.optString("value", null)
            is String -> description
            else -> null
        }

        println(result)
        return@withContext result
    }

    suspend fun getAuthorNameFromWKey(wKey: String?): String? = withContext(Dispatchers.IO) {
        if (wKey == null) return@withContext null

        val url = "https://openlibrary.org/$wKey.json"
        val body: String = client.get(url).body()
        val jsonObject = JSONObject(body)

        val authorsArray = jsonObject.optJSONArray("authors")
        if (authorsArray != null && authorsArray.length() > 0) {
            val authorKey = authorsArray.getJSONObject(0).getJSONObject("author").optString("key", null)

            if (authorKey != null) {
                val authorBody: String = client.get("https://openlibrary.org$authorKey.json").body()
                val authorName = JSONObject(authorBody).optString("name", null)

                println("Author: $authorName")
                return@withContext authorName
            }
        }
        return@withContext null
    }

    suspend fun getCoverUrlFromWKey(wKey: String?): String? = withContext(Dispatchers.IO) {
        if (wKey == null) return@withContext null

        try {
            val url = "https://openlibrary.org/$wKey.json"
            val body: String = client.get(url).body()
            val jsonObject = JSONObject(body)

            val coversArray = jsonObject.optJSONArray("covers")
            if (coversArray != null && coversArray.length() > 0) {
                for (i in 0 until coversArray.length()) {
                    val coverId = coversArray.optInt(i, -1)
                    if (coverId > 0) {
                        val coverUrl = "https://covers.openlibrary.org/b/id/$coverId-M.jpg"
                        println("Cover URL: $coverUrl")
                        return@withContext coverUrl
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return@withContext null
    }

}