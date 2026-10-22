package com.example.myapplication

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase

@Database(entities = [ItemPesquisa::class, ItemFavorito::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pesquisaDao(): PesquisaDAO
    abstract fun favoritoDao(): FavoritoDao // <-- Adicionado o DAO de favoritos

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}