package it.reply.webinardemo.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import it.reply.webinardemo.android.presentation.screen_my_pokemons.ScreenMyPokemons
import it.reply.webinardemo.android.presentation.screen_pokemon_details.ScreenPokemonDetails

@Composable
fun MainActivityNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavigationItem.MyPokemons.route) {
        composable(NavigationItem.MyPokemons.route) {
            ScreenMyPokemons(
                onPokemonDetailsClick = { pokemonId ->
                    navHostController.navigate("details/${pokemonId}") {
                        popUpTo(NavigationItem.MyPokemons.route) { saveState = true }
                    }
                })
        }

        composable(NavigationItem.PokemonBattles.route) {
            //ScreenPokemonBattles()
        }

        composable(NavigationItem.PokemonInfirmary.route) {
            //ScreenPokemonInfirmary()
        }

        composable(route = "details/{pokemonId}", arguments = listOf(navArgument("pokemonId") {
            type = NavType.IntType
        })) {
            ScreenPokemonDetails()
        }

    }
}