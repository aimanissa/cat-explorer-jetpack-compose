package com.aimanissa.catexplorer.presentation.ui.random.screen

sealed class RandomImageEvents {
    data object OnImageClick : RandomImageEvents()
    data object OnRefreshClick : RandomImageEvents()
}