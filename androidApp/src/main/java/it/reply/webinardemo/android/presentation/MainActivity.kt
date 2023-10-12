package it.reply.webinardemo.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import it.reply.webinardemo.android.presentation.navigation.BottomNavigationBar
import it.reply.webinardemo.android.presentation.navigation.MainActivityNavGraph
import pokedex.eu.reply.presentation.theme.Turbo

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navHostController = rememberNavController()


            PokedexTheme {
                MainScreen(navHostController = navHostController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navHostController) },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .background(Turbo)
                    .padding(paddingValues)
            ) {
                MainActivityNavGraph(navHostController = navHostController)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        MainScreen(navHostController = rememberNavController())
    }
}