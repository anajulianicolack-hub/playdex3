package com.example.myapplication

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update

@Dao
interface FavoritoDao {
    @Insert
    suspend fun inserir(favorito: ItemFavorito)

    @Query("SELECT * FROM tabela_favoritos ORDER BY id DESC")
    suspend fun buscarFavoritos(): List<ItemFavorito>

    @Query("SELECT * FROM tabela_favoritos WHERE id = :id")
    suspend fun buscarPorId(id: Int): ItemFavorito?
    @Update
    suspend fun atualizarFavorito(favorito: ItemFavorito)

    @Delete
    suspend fun removerFavorito(favorito: ItemFavorito)
}