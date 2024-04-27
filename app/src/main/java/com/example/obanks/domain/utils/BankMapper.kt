package com.example.obanks.domain.utils

import com.example.obanks.data.datasources.remote.OBanksResponseItem
import com.example.obanks.domain.entities.Bank

class BankMapper {

    fun castFromOBanksResponseToEntity(banksResponseItem: OBanksResponseItem) = Bank(
        name = banksResponseItem.bankName,
        description = banksResponseItem.description,
        age = banksResponseItem.age,
        photoUrl = banksResponseItem.url
    )

}