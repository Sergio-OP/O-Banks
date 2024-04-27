package com.example.obanks.data.repositories

import com.example.obanks.data.datasources.local.BankEntityDao
import com.example.obanks.data.datasources.remote.OBanksService
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.utils.BankMapper
import javax.inject.Inject

class BanksRepositoryImpl @Inject constructor(
    private val banksService: OBanksService,
    private val bankEntityDao: BankEntityDao,
) : BanksRepository {

    override suspend fun fetchBanks(): List<Bank> {
        val response = banksService
            .fetchBanks()
            .map {
                BankMapper().castFromOBanksResponseToEntity(it)
            }
        return response
    }

    override suspend fun getBankById(id: Int): Bank =
        BankMapper().castFromBankEntityToEntity(bankEntityDao.getBankById(id))

    override suspend fun insertAll(banks: List<Bank>) {
        val bankEntities = banks.map { BankMapper().castFromEntityToBankEntity(it) }
        bankEntityDao.insertAll(bankEntities)
    }

    override suspend fun getAll(): List<Bank> {
        val bankEntities = bankEntityDao.getAll()
        return bankEntities.map { BankMapper().castFromBankEntityToEntity(it) }
    }


}