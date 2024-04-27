package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import javax.inject.Inject

class SaveBanksUseCase @Inject constructor(
    private val banksRepository: BanksRepository
) {

    suspend operator fun invoke(banks: List<Bank>) {
        try {
            banksRepository.insertAll(banks)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}