package com.aimanissa.catexplorer.domain

import com.aimanissa.catexplorer.data.utils.RequestResult
import com.aimanissa.catexplorer.data.utils.mapTo
import com.aimanissa.catexplorer.domain.model.CatImage
import com.aimanissa.catexplorer.presentation.ui.random.model.CatImageUI
import com.aimanissa.catexplorer.presentation.ui.random.toCatImageUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CatImageUseCase @Inject constructor(
    private val repository: CatImageRepository
) {
    operator fun invoke(): Flow<RequestResult<CatImageUI>> =
        repository.getRandomCatImage()
            .map { result -> result.mapTo { catImage: CatImage -> catImage.toCatImageUI() } }

}