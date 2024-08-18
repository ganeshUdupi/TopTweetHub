package com.ganesh.toptweethub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ganesh.toptweethub.api.TopTweetHubApi
import com.ganesh.toptweethub.screens.CategoryScreen
import com.ganesh.toptweethub.screens.DetailScreen
import com.ganesh.toptweethub.ui.theme.TopTweetHubTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(topBar = {
                 TopAppBar(title = {
                     Text(text = "TopTweetHub")
                 }, colors =TopAppBarDefaults.topAppBarColors(
                     containerColor = Color.Black,
                     titleContentColor = Color.White
                 ))
            }) {
                Box(modifier = Modifier.padding(it)) {
                    App()
                }
            }

        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen {
                navController.navigate("detail/$it")
            }
        }
        composable(
            route = "detail/{category}",
            arguments = listOf(navArgument("category")
            {
                type = NavType.StringType
            })
        ) {
            DetailScreen()
        }
    }
}