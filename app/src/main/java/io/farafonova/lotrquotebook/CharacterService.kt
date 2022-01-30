package io.farafonova.lotrquotebook

import retrofit2.Response
import retrofit2.http.*

interface CharacterService {
    @GET("character")
    suspend fun findCharacters(
        @Header("Accept") contentType: String,
        @Header("Authorization") key: String,
        @Query("name") name: String
    ): Response<GeneralResponseBody<Character>>

    @GET("character/{id}/quote")
    suspend fun findCharacterQuotes(
        @Header("Accept") contentType: String,
        @Header("Authorization") key: String,
        @Path("id") characterId: String
    ): Response<GeneralResponseBody<CharacterQuote>>
}