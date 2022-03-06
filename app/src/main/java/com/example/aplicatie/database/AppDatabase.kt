package com.example.aplicatie.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplicatie.daos.CalculQuery
import com.example.aplicatie.daos.EmailQuery
import com.example.aplicatie.daos.IstoricQuery
import com.example.aplicatie.entities.Calcul
import com.example.aplicatie.entities.InfoEmail
import com.example.aplicatie.entities.Listoric

@Database(entities = arrayOf(Listoric::class, InfoEmail::class, Calcul::class), version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun istoricDao(): IstoricQuery
    abstract fun emailDao(): EmailQuery
    abstract fun calculDao(): CalculQuery
}

