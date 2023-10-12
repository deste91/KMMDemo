package it.reply.webinardemo.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonRemote(
    @SerialName("id")
    val id: Int,
    @SerialName("moves")
    val moves: List<Move>,
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val sprites: Sprites,
    @SerialName("stats")
    val stats: List<Stat>
) {
    @Serializable
    data class Move(
        @SerialName("move")
        val move: MoveSpecs,
    ) {
        @Serializable
        data class MoveSpecs(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }
    @Serializable
    data class Sprites(
        @SerialName("other")
        val other: Other
    ) {
        @Serializable
        data class Other(
            @SerialName("dream_world")
            val dreamWorld: DreamWorld,
            @SerialName("official-artwork")
            val officialArtwork: OfficialArtwork,
        ) {
            @Serializable
            data class DreamWorld(
                @SerialName("front_default")
                val frontDefault: String
            )
            @Serializable
            data class OfficialArtwork(
                @SerialName("front_default")
                val frontDefault: String
            )
        }
    }

    @Serializable
    data class Stat(
        @SerialName("base_stat")
        val baseStat: Int,
        @SerialName("stat")
        val stat: StatSpecs
    ) {
        @Serializable
        data class StatSpecs(
            @SerialName("name")
            val name: String,
            @SerialName("url")
            val url: String
        )
    }


}