package com.example.obanks.data.repositories

import com.example.obanks.data.datasources.local.BankEntityDao
import com.example.obanks.data.datasources.remote.OBanksService
import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import com.example.obanks.domain.utils.BankMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getBankById(id: Int): Flow<Bank> {
        return bankEntityDao.getBankById(id).map { BankMapper().castFromBankEntityToEntity(it) }
    }


    override suspend fun insertAll(banks: List<Bank>) {
        val bankEntities = banks.map { BankMapper().castFromEntityToBankEntity(it) }
        bankEntityDao.insertAll(bankEntities)
    }

    override fun getAll(): Flow<List<Bank>> {
        return bankEntityDao.getAll().map {
            it.map { bankEntity ->
                BankMapper().castFromBankEntityToEntity(bankEntity)
            }
        }
    }


    override suspend fun update(bank: Bank) =
        bankEntityDao.updateBank(BankMapper().castFromEntityToBankEntity(bank))

    override fun getBanksByName(name: String): Flow<List<Bank>> {
        return bankEntityDao.getBanksByName(name).map {
            it.map { bankEntity ->
                BankMapper().castFromBankEntityToEntity(bankEntity)
            }
        }
    }


}