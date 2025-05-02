package com.aimanissa.catexplorer.presentation.ui.random

import com.aimanissa.catexplorer.data.utils.RequestResult
import com.aimanissa.catexplorer.domain.model.CatImage
import com.aimanissa.catexplorer.presentation.ui.random.model.CatImageUI
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
    url = url
)