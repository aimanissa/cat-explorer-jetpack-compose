package com.aimanissa.catexplorer.presentation.ui.random.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aimanissa.catexplorer.presentation.theme.CatExplorerTheme
import com.aimanissa.catexplorer.presentation.ui.random.RandomImageViewModel

@Composable
fun RandomImageScreen(modifier: Modifier = Modifier, viewModel: RandomImageViewModel) {
    val state by viewModel.state.collectAsState()
    val currentState = state
    RandomImageContent(
        currentState,
        modifier,
        onImageClicked = { viewModel.handleIntent(RandomImageEvents.OnImageClick) },
        onRefreshClicked = { viewModel.handleIntent(RandomImageEvents.OnRefreshClick) }
    )
}

@Composable
private fun RandomImageContent(
    currentState: RandomImageState,
    modifier: Modifier = Modifier,
    onImageClicked: () -> Unit,
    onRefreshClicked: () -> Unit
) {
    Column(modifier) {
        when (currentState) {
            is RandomImageState.None -> Unit
            is RandomImageState.Error -> ErrorMessage(onRefreshClicked = onRefreshClicked)
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
    onRefreshClicked: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .background(CatExplorerTheme.colorScheme.error)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Error during update",
            color = CatExplorerTheme.colorScheme.onError,
            style = TextStyle(fontSize = 20.sp)
        )
        Button(modifier = Modifier.size(150.dp, 50.dp), onClick = onRefreshClicked) {
            Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "Refresh icon")
            Text(text = "Refresh")
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

@Preview(showBackground = true)
@Composable
fun ErrorMessagePreview() {
    CatExplorerTheme {
        ErrorMessage {}
    }
}
