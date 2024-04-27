package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetBankByIdUseCase @Inject constructor(
    private val banksRepository: BanksRepository,
) {

    operator fun invoke(id: Int): Flow<Bank> {
        return try {
            banksRepository.getBankById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyFlow()
        }
    }

}