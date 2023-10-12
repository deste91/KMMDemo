package it.reply.webinardemo.android.presentation.screen_pokemon_details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.reply.webinardemo.android.presentation.components.AutoResizeText
import it.reply.webinardemo.android.presentation.components.StandardAsyncImage
import it.reply.webinardemo.android.presentation.components.StandardStatsTable
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import pokedex.eu.reply.presentation.theme.Mariner

@Composable
fun ScreenPokemonDetails(viewModel: ScreenPokemonDetailsVM = viewModel()) {
    val pokemonData by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        pokemonData.pokemon?.let { pokemon ->
            AutoResizeText(
                text = pokemon.name.replaceFirstChar(kotlin.Char::titlecase),
                style = MaterialTheme.typography.headlineLarge,
                color = Mariner
            )

            StandardAsyncImage(url = pokemon.imageUrl, modifier = Modifier
                .height(300.dp)
                .width(300.dp))

            val listOfStats =
                listOf("HP", "Attack", "Defense", "Special attack", "Special defense", "Speed")


            StandardStatsTable(
                titleColumnOne = "Statistic",
                titleColumnTwo = "Value",
                elementsColumnOne = listOfStats,
                elementsColumnTwo = pokemon.stats.filter {
                    it.name in listOfStats.map { stat ->
                        stat.lowercase().replace(oldChar = ' ', newChar = '-')
                    }
                }.map { it.baseStat.toString() },
                modifier = Modifier
                    .padding(15.dp)
                    .border(1.dp, Mariner, RoundedCornerShape(10))
            )

            StandardStatsTable(
                titleColumnOne = "Move",
                titleColumnTwo = "Damage",
                elementsColumnOne = pokemon.moves.map {
                    it.name.replaceFirstChar(kotlin.Char::titlecase)
                        .replace(oldChar = '-', newChar = ' ')
                },
                elementsColumnTwo = pokemon.moves.map { "15" },
                modifier = Modifier
                    .padding(15.dp)
                    .border(1.dp, Mariner, RoundedCornerShape(10))
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ScreenPokemonDetailsPreview() {
    PokedexTheme {
        ScreenPokemonDetails()
    }

}