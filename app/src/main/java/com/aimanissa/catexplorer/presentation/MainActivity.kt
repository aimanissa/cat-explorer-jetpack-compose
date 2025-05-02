package com.aimanissa.catexplorer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.aimanissa.catexplorer.presentation.theme.CatExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatExplorerTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = CatExplorerTheme.colorScheme.background)
                ) { innerPadding ->
                    CatExplorerNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

