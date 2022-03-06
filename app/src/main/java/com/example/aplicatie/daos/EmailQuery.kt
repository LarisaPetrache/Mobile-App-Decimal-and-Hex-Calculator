package com.example.aplicatie.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.aplicatie.entities.InfoEmail

@Dao
interface EmailQuery {
    @Query("SELECT * FROM InfoEmail")
    fun getALL(): List<InfoEmail>

    @Insert
    fun insertAll(vararg email: InfoEmail)

    @Delete
    fun delete(email: InfoEmail)
}