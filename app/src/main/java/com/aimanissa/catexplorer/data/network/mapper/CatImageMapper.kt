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
        breed = breeds.getFirstOrNull()
    )
}

private fun List<BreedDTO>?.getFirstOrNull(): Breed? = this?.getOrNull(FIRST_INDEX)?.toBreed()

private fun BreedDTO.toBreed(): Breed {
    return Breed(
        id = id,
        weight = weight.toWeight(),
        name = name,
        description = description,
        temperament = temperament,
        origin = origin,
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

private const val FIRST_INDEX = 0