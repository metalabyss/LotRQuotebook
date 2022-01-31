package io.farafonova.lotrquotebook

import android.app.Application

class QuotebookApplication: Application() {
    private val apiKey by lazy { applicationContext.getString(R.string.api_key) }
    private val baseUrl by lazy { applicationContext.getString(R.string.base_url) }

    val repository by lazy { CharacterRepository(baseUrl, apiKey) }
}