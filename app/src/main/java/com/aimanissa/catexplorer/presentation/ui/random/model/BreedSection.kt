package com.aimanissa.catexplorer.presentation.ui.random.model

data class BreedSection(val header: BreedHeader, val text: String)

enum class BreedHeader(val value: String) {
    Name("Name"),
    Description("Description"),
    CountryOfOrigin("Country of origin"),
    Temperament("Temperament"),
    LifeSpan("Life span"),
    Wikipedia("Wikipedia")
}

