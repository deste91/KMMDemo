package it.reply.webinardemo.android.presentation.screen_pokemon_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.reply.webinardemo.domain.model.Pokemon
import it.reply.webinardemo.repository.PokemonSharedRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScreenPokemonDetailsVM(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val pokemonRepo: PokemonSharedRepo = PokemonSharedRepo()

    private val pokemonId: Int = checkNotNull(savedStateHandle["pokemonId"])

    data class UiState(
        val isLoading: Boolean,
        val pokemon: Pokemon?
    )

    val uiState: MutableStateFlow<UiState> =
        MutableStateFlow(
            UiState(
                isLoading = true,
                pokemon = null
            )
        )

    init {
        fetchPokemonDetails()
    }

    private fun fetchPokemonDetails() {
        uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pokemonDetails = pokemonRepo.getPokemonById(pokemonId).getOrNull()
                uiState.update { it.copy(pokemon = pokemonDetails, isLoading = false) }
            }
        }
    }


}