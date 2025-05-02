package com.aimanissa.catexplorer.data.network.mapper

import com.aimanissa.catexplorer.data.network.model.BreedDTO
import com.aimanissa.catexplorer.data.network.model.CatImageDTO
import com.aimanissa.catexplorer.data.network.model.WeightDTO
import com.aimanissa.catexplorer.domain.model.Breed
import com.aimanissa.catexplorer.domain.model.CatImage
import com.aimanissa.catexplorer.domain.model.Weight

fun CatImageDTO.toCatImage(): CatImage {
    return CatImage(
        id = id,
        width = width,
        height = height,
        url = url,
        breeds = breeds?.map(BreedDTO::toBreed)
    )
}

private fun BreedDTO.toBreed(): Breed {
    return Breed(
        id = id,
        weight = weight.toWeight(),
        name = name,
        temperament = temperament,
        origin = origin,
        countryCodes = countryCodes,
        countryCode = countryCode,
        lifeSpan = lifeSpan,
        wikipediaUrl = wikipediaUrl
    )
}

private fun WeightDTO.toWeight(): Weight {
    return Weight(
        imperial = imperial,
        metric = metric
    )
}