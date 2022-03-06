package com.example.aplicatie.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Listoric(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "rand") val rand: String?,
)
