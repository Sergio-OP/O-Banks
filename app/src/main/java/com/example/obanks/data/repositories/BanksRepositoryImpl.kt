package com.example.obanks.data.repositories

import com.example.obanks.data.datasources.remote.OBanksService
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.utils.BankMapper
import javax.inject.Inject

class BanksRepositoryImpl @Inject constructor(
    private val banksService: OBanksService
): BanksRepository {



    override suspend fun fetchBanks(): List<Bank> {
        val response = banksService
            .fetchBanks()
            .map {
                BankMapper().castFromOBanksResponseToEntity(it)
            }
        return response
    }

    override suspend fun fetchFavoriteBanks(): List<Bank> {
        TODO("Not yet implemented")
    }

}