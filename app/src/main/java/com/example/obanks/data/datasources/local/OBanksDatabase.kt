package com.example.obanks.data.datasources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BankEntity::class], version = 1, exportSchema = false)
abstract class OBanksDatabase: RoomDatabase() {

    abstract fun bankEntityDao(): BankEntityDao

    companion object {
        @Volatile
        private var Instance: OBanksDatabase? = null

        fun getDatabase(context: Context): OBanksDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OBanksDatabase::class.java, "o_banks_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }

}