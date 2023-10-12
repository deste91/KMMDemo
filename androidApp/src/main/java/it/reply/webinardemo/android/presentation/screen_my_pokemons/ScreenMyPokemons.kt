package it.reply.webinardemo.android.presentation.screen_my_pokemons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import it.reply.webinardemo.android.R
import it.reply.webinardemo.android.presentation.components.AutoResizeText
import it.reply.webinardemo.android.presentation.components.StandardAsyncImage
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import it.reply.webinardemo.domain.model.Pokemon
import pokedex.eu.reply.presentation.theme.Mariner
import pokedex.eu.reply.presentation.theme.White

@Composable
fun ScreenMyPokemons(
    screenMyPokemonsVM: ScreenMyPokemonsVM = viewModel(),
    onPokemonDetailsClick: (Int) -> Unit
) {
    val state by screenMyPokemonsVM.uiState.collectAsState()

    PokedexTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

/*            PokemonSearchBar(onSearchText = { textSearch ->
                screenMyPokemonsVM.searchPokemon(textSearch = textSearch)
            })*/

            if (state.isLoading) {
                CreateCircularProgress()
            } else {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(false),
                    onRefresh = { screenMyPokemonsVM.fetchPokemonsTeam() },
                ) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        MyPokemonsList(
                            pokemonsData = state.pokemonsTeam,
                            onPokemonDetailsClick = onPokemonDetailsClick
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PokemonSearchBarPreview() {
    PokedexTheme {
        PokemonSearchBar(onSearchText = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchBar(onSearchText: (String) -> Unit) {

    var searchText by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Mariner)
            .padding(10.dp)
    ) {
        SearchBar(
            query = searchText,
            onQueryChange = {
                searchText = it
                onSearchText(searchText)
            },
            onSearch = {
                focusManager.clearFocus()
            },
            colors = SearchBarDefaults.colors(
                containerColor = White
            ),
            active = false,
            onActiveChange = {},
            placeholder = { Text("Insert pokemon to search") },
            trailingIcon = {
                if (searchText.isEmpty()) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_search_24),
                        contentDescription = "Icon"
                    )
                } else {
                    IconButton(onClick = {
                        searchText = ""
                        onSearchText(searchText)
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_close_24),
                            contentDescription = "Icon"
                        )
                    }
                }
            }
        ) { }

    }
}


@Composable
fun CreateCircularProgress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(White)
            .fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun CreateCircularProgressPreview() {
    PokedexTheme {
        CreateCircularProgress()
    }
}

@Composable
fun MyPokemonsList(
    pokemonsData: List<Pokemon>,
    onPokemonDetailsClick: (Int) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 125.dp)
    ) {
        items(pokemonsData) { pokemon ->
            MyLazyGridItem(
                pokemonName = pokemon.name,
                pokemonImageUrl = pokemon.imageUrl,
                pokemonId = pokemon.id,
                onPokemonDetailsClick = onPokemonDetailsClick
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPokemonsListPreview() {
    PokedexTheme {
        MyPokemonsList(pokemonsData = listOf(Pokemon.mock()), onPokemonDetailsClick = {})
    }

}

@Composable
fun MyLazyGridItem(
    pokemonName: String,
    pokemonImageUrl: String,
    pokemonId: Int,
    modifier: Modifier = Modifier,
    onPokemonDetailsClick: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .border(0.5.dp, LightGray)
            .height(175.dp)
            .clickable { onPokemonDetailsClick(pokemonId) }
    ) {
        StandardAsyncImage(
            url = pokemonImageUrl,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )

        AutoResizeText(text = pokemonName.replaceFirstChar(Char::titlecase))
    }
}

@Preview
@Composable
fun MyLazyGridItemPreview() {
    PokedexTheme {
        MyLazyGridItem(
            pokemonName = Pokemon.mock().name,
            pokemonImageUrl = Pokemon.mock().imageUrl,
            pokemonId = Pokemon.mock().id,
            onPokemonDetailsClick = {}
        )
    }
}