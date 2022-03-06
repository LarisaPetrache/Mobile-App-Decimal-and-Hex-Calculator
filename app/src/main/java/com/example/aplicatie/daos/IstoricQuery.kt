package com.example.aplicatie.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aplicatie.entities.Listoric

@Dao
interface IstoricQuery {
    @Query("SELECT * FROM Listoric")
    fun getALL(): List<Listoric>

    @Insert
    fun insertAll(vararg rand: Listoric)

    @Delete
    fun delete(rand: Listoric)

    @Query("DELETE FROM Listoric")
    fun clearTable()
}

