package com.aimanissa.catexplorer.presentation.ui.random.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.aimanissa.catexplorer.presentation.theme.CatExplorerTheme
import com.aimanissa.catexplorer.presentation.ui.random.RandomImageViewModel

@Composable
fun RandomImageScreen(modifier: Modifier = Modifier, viewModel: RandomImageViewModel) {
    val state by viewModel.state.collectAsState()
    val currentState = state
    RandomImageContent(
        currentState,
        modifier,
        onImageClicked = { viewModel.handleIntent(RandomImageEvents.OnImageClick) })
}

@Composable
private fun RandomImageContent(
    currentState: RandomImageState,
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit
) {
    Column(modifier) {
        when (currentState) {
            is RandomImageState.None -> Unit
            is RandomImageState.Error -> ErrorMessage()
            is RandomImageState.Loading -> ProgressIndicator()
            is RandomImageState.Success -> RandomImage(
                currentState,
                onImageClicked = onImageClicked
            )
        }
    }
}

@Composable
private fun ErrorMessage(
    modifier: Modifier = Modifier,
) {
    Column {
        Box(
            modifier
                .fillMaxSize()
                .background(CatExplorerTheme.colorScheme.error)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Error during update", color = CatExplorerTheme.colorScheme.onError)
        }
    }
}

@Composable
private fun ProgressIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.Center,

        ) {
        CircularProgressIndicator(
            color = CatExplorerTheme.colorScheme.onSurface,
            trackColor = CatExplorerTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
private fun RandomImage(
    state: RandomImageState.Success,
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var isImageVisible by remember { mutableStateOf(true) }
        if (isImageVisible) {
            AsyncImage(
                modifier = Modifier.clickable(enabled = true, onClick = onImageClicked),
                onState = { state ->
                    if (state is AsyncImagePainter.State.Error) {
                        isImageVisible = false
                    }
                },
                model = state.catImage.url,
                contentDescription = "cat image",
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}
