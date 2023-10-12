package it.reply.webinardemo.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val stats: List<Statistics> = listOf(),
    val moves: List<Move> = listOf()
) {
    data class Statistics(
        val name: String,
        val baseStat: Int,
        val link: String
    )

    data class Move(
        val name: String,
        val link: String
    )

    companion object {
        fun mock(): Pokemon {
            return Pokemon(
                id = 4,
                name = "charmander",
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",
                stats = listOf(
                    Statistics(
                        name = "hp",
                        baseStat = 39,
                        link = ""
                    )
                ),
                moves = listOf(
                    Move(
                        name = "mega-punch",
                        link = ""
                    )
                )
            )
        }
    }
}