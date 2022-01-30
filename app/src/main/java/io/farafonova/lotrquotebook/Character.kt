package io.farafonova.lotrquotebook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Character(
    @JsonProperty("_id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("gender") val gender: String,
    @JsonProperty("race") val race: String,
    @JsonProperty("realm") val realm: String
)
