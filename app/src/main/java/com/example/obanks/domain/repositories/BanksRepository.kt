package com.example.obanks.domain.repositories

import com.example.obanks.domain.entities.Bank

interface BanksRepository {
    suspend fun fetchBanks(): List<Bank>
    suspend fun getBankById(id: Int): Bank

    suspend fun insertAll(banks: List<Bank>)

    suspend fun getAll(): List<Bank>
}