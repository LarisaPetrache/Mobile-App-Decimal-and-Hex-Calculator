package com.example.aplicatie.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InfoEmail(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "data") val data: String?,
    @ColumnInfo(name = "ora") val ora: String?,
)
