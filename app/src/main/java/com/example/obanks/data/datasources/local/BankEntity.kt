package com.example.obanks.data.datasources.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class BankEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val age: Int,
    @ColumnInfo val isFavorite: Boolean,
    @ColumnInfo(name = "photo_url") val photoUrl: String,
)
