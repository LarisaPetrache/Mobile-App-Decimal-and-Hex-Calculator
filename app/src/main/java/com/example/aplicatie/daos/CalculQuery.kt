package com.example.aplicatie.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aplicatie.entities.Calcul

@Dao
interface CalculQuery {
    @Query("SELECT * FROM Calcul")
    fun getALL(): List<Calcul>

    @Insert
    fun insertAll(vararg calcul: Calcul)

    @Delete
    fun delete(calcul: Calcul)

    @Query("DELETE FROM Calcul")
    fun clearTable()
}