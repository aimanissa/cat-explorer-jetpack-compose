package com.aimanissa.catexplorer.presentation.ui.random

import com.aimanissa.catexplorer.data.utils.RequestResult
import com.aimanissa.catexplorer.domain.model.Breed
import com.aimanissa.catexplorer.domain.model.CatImage
import com.aimanissa.catexplorer.domain.model.Weight
import com.aimanissa.catexplorer.presentation.ui.random.model.BreedHeader
import com.aimanissa.catexplorer.presentation.ui.random.model.BreedSection
import com.aimanissa.catexplorer.presentation.ui.random.model.BreedUI
import com.aimanissa.catexplorer.presentation.ui.random.model.CatImageUI
import com.aimanissa.catexplorer.presentation.ui.random.model.WeightUI
import com.aimanissa.catexplorer.presentation.ui.random.screen.RandomImageState

internal fun RequestResult<CatImageUI>.toUiState(): RandomImageState {
    return when (this) {
        is RequestResult.Error -> RandomImageState.Error
        is RequestResult.InProgress -> RandomImageState.Loading
        is RequestResult.Success -> RandomImageState.Success(data)
    }
}

fun CatImage.toCatImageUI() = CatImageUI(
    id = id,
    width = width,
    height = height,
    url = url,
    breed = breed?.toBreedUI()
)

fun Breed.toBreedUI() = BreedUI(
    id = id,
    weight = weight.toWeightUI(),
    name = name,
    description = description,
    temperament = temperament,
    origin = origin,
    lifeSpan = lifeSpan,
    wikipediaUrl = wikipediaUrl
)

fun Weight.toWeightUI() = WeightUI(metric = metric)

fun BreedUI.toBreedSections(): List<BreedSection> {
    return listOf(
        BreedSection(BreedHeader.Name, name),
        BreedSection(BreedHeader.Description, description),
        BreedSection(BreedHeader.CountryOfOrigin, origin),
        BreedSection(BreedHeader.Temperament, temperament),
        BreedSection(BreedHeader.LifeSpan, "$lifeSpan years"),
        BreedSection(BreedHeader.Wikipedia, wikipediaUrl)
    )
}