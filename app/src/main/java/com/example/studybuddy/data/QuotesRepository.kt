package com.example.studybuddy.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

object QuotesRepository {

    private val client = OkHttpClient()

    suspend fun getRandomQuote(): String = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("https://zenquotes.io/api/random")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@withContext "Server error: ${response.code}"
                }

                val body = response.body?.string() ?: return@withContext "Empty response"
                val jsonArray = JSONArray(body)
                val json = jsonArray.getJSONObject(0)
                val quote = json.getString("q")
                val author = json.getString("a")
                "\"$quote\"\n\n— $author"
            }
        } catch (e: Exception) {
            "Failed to load quote: ${e::class.simpleName} — ${e.message}"
        }
    }
}
