package com.example.myapplication

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "tabela_favoritos")
data class ItemFavorito(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val status: String,
    val review: String,
    val nota: Int,
    val data: String
)