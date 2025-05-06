package com.aimanissa.catexplorer.domain.model

class CatImage(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val breed: Breed? = null
)

data class Breed(
    val weight: Weight,
    val id: String,
    val name: String,
    val description: String,
    val temperament: String,
    val origin: String,
    val lifeSpan: String,
    val wikipediaUrl: String
)

data class Weight(
    val imperial: String,
    val metric: String
)