package com.example.obanks.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BankEntityDao {
    @Query("SELECT * FROM banks")
    fun getAll(): Flow<List<BankEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(banks: List<BankEntity>)

    @Update
    suspend fun updateBank(bank: BankEntity)

    @Query("SELECT * from banks WHERE id = :id LIMIT 1")
    fun getBankById(id: Int): Flow<BankEntity>
}