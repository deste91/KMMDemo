package it.reply.webinardemo.android.presentation.screen_my_pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reply.webinardemo.domain.model.Pokemon
import it.reply.webinardemo.repository.PokemonSharedRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScreenMyPokemonsVM() : ViewModel() {

    private val pokemonRepo: PokemonSharedRepo = PokemonSharedRepo()

    data class UiState(
        val isLoading: Boolean,
        val pokemonsTeam: List<Pokemon>
    )


    val uiState: MutableStateFlow<UiState> =
        MutableStateFlow(
            UiState(
                isLoading = true,
                pokemonsTeam = emptyList(),
            )
        )

    init {
        fetchPokemonsTeam()
    }

    fun fetchPokemonsTeam() {
        uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val team = pokemonRepo.fetchPokemonsTeam()
                uiState.update { it.copy(pokemonsTeam = team, isLoading = false) }
            }
        }
    }

/*    fun searchPokemon(textSearch: String) {
        uiState.update {
            it.copy(pokemons = uiState.value.allUserPokemons.filter { pokemon ->
                pokemon.name.contains(
                    textSearch
                )
            })
        }
    }*/

}
