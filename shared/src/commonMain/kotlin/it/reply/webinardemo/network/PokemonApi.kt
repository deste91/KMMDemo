package it.reply.webinardemo.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import it.reply.webinardemo.network.model.PokemonRemote
import kotlinx.serialization.json.Json

private const val BASE_POKEMON_URL = "https://pokeapi.co/api/v2/"

class PokemonApi {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getPokemon(id: Int): Result<PokemonRemote> {
        val url = "${BASE_POKEMON_URL}pokemon/$id"
        val response = httpClient.get(url)
        return when(response.status){
            HttpStatusCode.OK -> Result.success(response.body())
            else -> Result.failure(Throwable("Remote call failed with code: ${response.status}"))
        }
    }

}