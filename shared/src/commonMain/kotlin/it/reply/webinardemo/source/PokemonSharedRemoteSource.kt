package it.reply.webinardemo.source

import it.reply.webinardemo.domain.model.Pokemon
import it.reply.webinardemo.network.PokemonApi
import it.reply.webinardemo.network.mapper.toDomain

class PokemonSharedRemoteSource {
    private val pokemonApi: PokemonApi = PokemonApi()

    @Throws(Exception::class)
    suspend fun getPokemon(id: Int): Result<Pokemon> {
        return pokemonApi.getPokemon(id).mapCatching { result -> result.toDomain() }
    }
}