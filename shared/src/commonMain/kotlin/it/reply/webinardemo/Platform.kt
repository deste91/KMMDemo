package it.reply.webinardemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform