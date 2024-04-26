package com.example.obanks.domain.entities

data class Bank(
    val name: String = "",
    val description: String = "",
    val age: Int = 0,
    val photoUrl: String = "",
    val isFavorite: Boolean = false
)
