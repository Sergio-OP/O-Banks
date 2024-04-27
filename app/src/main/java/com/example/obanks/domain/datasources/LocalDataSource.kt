package com.example.obanks.domain.datasources

import com.example.obanks.domain.entities.Bank

interface LocalDataSource {
    suspend fun fetchFavoriteBanks(): List<Bank>
}