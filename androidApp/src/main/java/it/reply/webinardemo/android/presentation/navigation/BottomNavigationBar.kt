package it.reply.webinardemo.android.presentation.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import pokedex.eu.reply.presentation.theme.Mariner
import pokedex.eu.reply.presentation.theme.Turbo
import pokedex.eu.reply.presentation.theme.White

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = remember {
        listOf(
            NavigationItem.MyPokemons,
            NavigationItem.PokemonBattles,
            NavigationItem.PokemonInfirmary
        )
    }

    NavigationBar(
        contentColor = Turbo,
        containerColor = Mariner
    ) {
        items.forEach { item ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val isSelected = currentDestination?.hierarchy?.any {
                it.route == item.route
            } == true

            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Mariner,
                    unselectedIconColor = Turbo,
                    selectedTextColor = Turbo,
                    unselectedTextColor = Turbo,
                    indicatorColor = White,
                ),
                alwaysShowLabel = true,
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    PokedexTheme {
        BottomNavigationBar(rememberNavController())
    }
}
