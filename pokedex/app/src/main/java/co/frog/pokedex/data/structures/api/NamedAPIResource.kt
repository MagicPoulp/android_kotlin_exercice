package co.frog.pokedex.data.structures.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class NamedAPIResource(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("url")
    val url: String,
)
