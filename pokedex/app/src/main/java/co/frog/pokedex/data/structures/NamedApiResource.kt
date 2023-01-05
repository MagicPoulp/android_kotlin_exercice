package co.frog.pokedex.data.structures

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class NamedApiResource(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("url")
    val url: String,
)
