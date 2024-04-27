package com.example.obanks.domain.entities

data class Bank(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val age: Int = 0,
    val photoUrl: String = "",
    val isFavorite: Boolean = false
)
