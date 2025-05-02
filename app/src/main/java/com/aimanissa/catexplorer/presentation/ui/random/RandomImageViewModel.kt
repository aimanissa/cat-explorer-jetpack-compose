package com.aimanissa.catexplorer.presentation.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aimanissa.catexplorer.domain.CatImageUseCase
import com.aimanissa.catexplorer.presentation.ui.random.screen.RandomImageEvents
import com.aimanissa.catexplorer.presentation.ui.random.screen.RandomImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltViewModel
class RandomImageViewModel @Inject constructor(
    private val useCase: Provider<CatImageUseCase>
) : ViewModel() {

    private var _state: MutableStateFlow<RandomImageState> = MutableStateFlow(RandomImageState.None)
    val state: StateFlow<RandomImageState> = _state.asStateFlow()

    init {
        handleIntent(RandomImageEvents.OnImageClick)
    }

    fun handleIntent(event: RandomImageEvents) {
        when (event) {
            is RandomImageEvents.OnImageClick -> loadImage()

        }
    }

    private fun loadImage() {
        viewModelScope.launch {
            useCase.get().invoke().map { it.toUiState() }.collect { value ->
                _state.value = value
            }
        }
    }
}