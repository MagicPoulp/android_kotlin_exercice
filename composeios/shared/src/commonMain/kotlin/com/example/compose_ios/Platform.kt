package com.example.compose_ios

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform