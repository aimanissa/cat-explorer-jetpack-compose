package com.aimanissa.catexplorer.presentation.ui.random.model

data class CatImageUI(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
    val breed: BreedUI? = null
)

data class BreedUI(
    val id: String,
    val weight: WeightUI,
    val name: String,
    val description: String,
    val temperament: String,
    val origin: String,
    val lifeSpan: String,
    val wikipediaUrl: String
)

data class WeightUI(
    val metric: String
)
