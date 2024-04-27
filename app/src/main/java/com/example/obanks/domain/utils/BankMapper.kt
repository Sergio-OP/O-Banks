package com.example.obanks.domain.utils

import com.example.obanks.data.datasources.local.BankEntity
import com.example.obanks.data.datasources.remote.OBanksResponseItem
import com.example.obanks.domain.entities.Bank

class BankMapper {

    fun castFromOBanksResponseToEntity(banksResponseItem: OBanksResponseItem) = Bank(
        name = banksResponseItem.bankName,
        description = banksResponseItem.description,
        age = banksResponseItem.age,
        photoUrl = banksResponseItem.url
    )

    fun castFromBankEntityToEntity(banksEntity: BankEntity) = Bank(
        id = banksEntity.id,
        name = banksEntity.name,
        description = banksEntity.description,
        age = banksEntity.age,
        photoUrl = banksEntity.photoUrl,
        isFavorite = banksEntity.isFavorite
    )

    fun castFromEntityToBankEntity(bank: Bank) = BankEntity(
        id = bank.id,
        name = bank.name,
        description = bank.description,
        age = bank.age,
        photoUrl = bank.photoUrl,
        isFavorite = bank.isFavorite
    )

}