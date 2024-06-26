package com.example.obanks.domain.use_cases

import com.example.obanks.domain.entities.Bank
import com.example.obanks.domain.repositories.BanksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class GetBanksUseCase @Inject constructor(
    private val banksRepository: BanksRepository
) {

    operator fun invoke(): Flow<List<Bank>> {
        return try {
            banksRepository.getAll()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyFlow()
        }
    }

}