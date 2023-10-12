package it.reply.webinardemo.network.mapper

import it.reply.webinardemo.domain.model.Pokemon
import it.reply.webinardemo.network.model.PokemonRemote

fun PokemonRemote.toDomain(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        imageUrl = this.sprites.other.officialArtwork.frontDefault,
        stats = this.stats.map {
            Pokemon.Statistics(
                name = it.stat.name,
                baseStat = it.baseStat,
                link = it.stat.url
            )
        }

    )
}