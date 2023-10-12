package it.reply.webinardemo.repository

import it.reply.webinardemo.domain.model.Pokemon
import it.reply.webinardemo.source.PokemonSharedRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PokemonSharedRepo {
    private val pokemonRemoteSource: PokemonSharedRemoteSource = PokemonSharedRemoteSource()

    suspend fun fetchPokemonsTeam(): List<Pokemon> {
        val context : CoroutineContext = Dispatchers.IO + SupervisorJob()
        var team: List<Pokemon> = emptyList()
        CoroutineScope(context).launch {
            team = listOf(9, 3, 6, 149, 25, 143).map { id ->
                async { getPokemonById(id) }
            }.awaitAll()
                .mapNotNull { pokemonRes ->
                    pokemonRes.fold(
                        onSuccess = { pokemon ->
                            pokemon
                        },
                        onFailure = {
                            println("Error fetching pokemon: $it")
                            null
                        }
                    )
                }
        }.join()
        return team
    }

    suspend fun getPokemonById(pokemonId: Int): Result<Pokemon> =
        pokemonRemoteSource.getPokemon(id = pokemonId)
}