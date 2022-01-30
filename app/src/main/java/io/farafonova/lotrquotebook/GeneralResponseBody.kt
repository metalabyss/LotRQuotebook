package io.farafonova.lotrquotebook

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GeneralResponseBody<T>(@JsonProperty("docs") val docs: List<T>)