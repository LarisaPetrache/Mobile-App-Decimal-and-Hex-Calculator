package com.example.aplicatie.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calcul(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "baza") val baza: String?,
    @ColumnInfo(name = "nr1") val nr1: String?,
    @ColumnInfo(name = "nr2") val nr2: String?,
    @ColumnInfo(name = "operatie") val operatie: String?,
    @ColumnInfo(name = "rezultat") val rezultat: String?,
    @ColumnInfo(name = "data") val data: String?,
    @ColumnInfo(name = "ora") val ora: String?,
)
