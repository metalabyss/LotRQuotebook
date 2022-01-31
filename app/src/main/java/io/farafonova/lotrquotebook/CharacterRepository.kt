package io.farafonova.lotrquotebook

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class CharacterRepository(baseUrl: String, apiKey: String) {
    private val authHeader = "Bearer $apiKey"
    private val TAG = CharacterRepository::class.qualifiedName

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    private val characterService: CharacterService = retrofit.create(CharacterService::class.java)

    suspend fun getCharacterByName(characterName: String): Character? {

        val response = characterService.findCharacters("application/json", authHeader, characterName)

        var character: Character? = null

        if (response.isSuccessful) {
            val characters = response.body()?.docs
            if (!characters.isNullOrEmpty()) {
                character = characters[0]
            }
        } else {
            Log.e(TAG, "Failed to get data.")
        }
        return character
    }

    fun getAllQuotesByCharacterId(characterId: String) {

    }
}