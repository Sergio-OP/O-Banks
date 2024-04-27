package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import javax.inject.Inject

class ToggleFavoriteBankUseCase @Inject constructor(
    private val banksRepository: BanksRepository
) {

    suspend operator fun invoke(bank: Bank) {
        try {
            banksRepository.update(bank)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}