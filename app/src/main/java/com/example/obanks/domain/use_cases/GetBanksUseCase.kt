package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(
    private val banksRepository: BanksRepository
) {

    suspend operator fun invoke(): List<Bank> {
        return try {
            banksRepository.fetchBanks()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

}