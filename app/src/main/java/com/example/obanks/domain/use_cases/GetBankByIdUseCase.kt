package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import javax.inject.Inject

class GetBankByIdUseCase @Inject constructor(
    private val banksRepository: BanksRepository,
) {

    suspend operator fun invoke(id: Int): Bank? {
        return try {
            banksRepository.getBankById(id)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}