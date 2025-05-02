package com.aimanissa.catexplorer.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatImageDTO(
    @SerialName("id") val id: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("url") val url: String,
    @SerialName("breeds") val breeds: List<BreedDTO>? = null
)

@Serializable
data class BreedDTO(
    @SerialName("weight") val weight: WeightDTO,
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("temperament") val temperament: String,
    @SerialName("origin") val origin: String,
    @SerialName("country_codes" )val countryCodes: String,
    @SerialName("country_code") val countryCode: String,
    @SerialName("life_span") val lifeSpan: String,
    @SerialName("wikipedia_url") val wikipediaUrl: String
)

@Serializable
data class WeightDTO(
    @SerialName("imperial") val imperial: String,
    @SerialName("metric") val metric: String
)
