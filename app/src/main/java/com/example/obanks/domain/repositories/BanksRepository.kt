package com.example.obanks.domain.repositories

import com.example.obanks.domain.entities.Bank

interface BanksRepository {
    suspend fun fetchBanks(): List<Bank>
    suspend fun fetchFavoriteBanks(): List<Bank>
}