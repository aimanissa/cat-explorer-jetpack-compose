package com.aimanissa.catexplorer.presentation.ui.random.screen

import com.aimanissa.catexplorer.presentation.ui.random.model.CatImageUI

sealed interface RandomImageState {
    data object None : RandomImageState
    data object Loading : RandomImageState
    data object Error : RandomImageState
    data class Success(val catImage: CatImageUI) : RandomImageState
}
