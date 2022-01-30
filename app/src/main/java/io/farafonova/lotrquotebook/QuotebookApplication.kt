package io.farafonova.lotrquotebook

import android.app.Application
import android.content.Context

class QuotebookApplication: Application() {
    val repository by lazy { CharacterRepository() }

    companion object {
        private lateinit var context: Context
        fun getContext() : Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}