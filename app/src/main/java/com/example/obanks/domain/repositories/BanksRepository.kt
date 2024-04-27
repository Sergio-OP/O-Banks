package com.example.obanks.domain.repositories

import com.example.obanks.domain.entities.Bank
import kotlinx.coroutines.flow.Flow

interface BanksRepository {
    suspend fun fetchBanks(): List<Bank>
    fun getBankById(id: Int): Flow<Bank>
    suspend fun insertAll(banks: List<Bank>)
    fun getAll(): Flow<List<Bank>>
    suspend fun update(bank: Bank)

    fun getBanksByName(name: String): Flow<List<Bank>>
}