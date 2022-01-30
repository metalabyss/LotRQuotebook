package io.farafonova.lotrquotebook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CharacterQuote(
    @JsonProperty("_id") val id: String,
    @JsonProperty("dialog") val dialog: String
)
