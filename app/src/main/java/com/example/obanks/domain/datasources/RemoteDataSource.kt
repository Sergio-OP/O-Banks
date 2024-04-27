package com.example.obanks.domain.datasources

import com.example.obanks.domain.entities.Bank

interface RemoteDataSource {
    suspend fun fetchBanks(): List<Bank>
}