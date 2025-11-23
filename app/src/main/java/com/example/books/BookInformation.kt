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

        // Sprawdzenie czy jest "docs" i czy są jakieś wyniki
        val docsArray = jsonObject.optJSONArray("docs")
        if (docsArray != null && docsArray.length() > 0) {
            val firstDoc = docsArray.getJSONObject(0)
            val key = firstDoc.optString("key", null) // bezpieczne pobranie
            println("Klucz: $key")
            return@withContext key
        }

        return@withContext null // brak wyników
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




}
