package it.reply.webinardemo.android.presentation.navigation

import it.reply.webinardemo.android.R

sealed class NavigationItem(val title: String, val icon: Int, val route: String) {
    object MyPokemons :
        NavigationItem("My Pokemon", R.drawable.baseline_catching_pokemon_24, "my_pokemons")

    object PokemonBattles :
        NavigationItem("Battles", R.drawable.baseline_catching_pokemon_24, "pokemon_battles")

    object PokemonInfirmary :
        NavigationItem("Infirmary", R.drawable.baseline_healing_24, "pokemon_infirmary")
}