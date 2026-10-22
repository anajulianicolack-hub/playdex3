package com.example.myapplication

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface FavoritoDao {
    @Insert
    fun inserir(favorito: ItemFavorito)

    @Query("SELECT * FROM tabela_favoritos ORDER BY id DESC")
    fun buscarFavoritos(): List<ItemFavorito>

    @Update
    fun atualizarFavorito(favorito: ItemFavorito)

    @Delete
    fun removerFavorito(favorito: ItemFavorito)
}