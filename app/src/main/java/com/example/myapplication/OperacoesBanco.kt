package com.example.myapplication

import android.util.Log

suspend fun inserirPesquisa(nome: String, pesquisaDao: PesquisaDAO) {
    try {
        pesquisaDao.inserir(ItemPesquisa(0, nome))
    } catch (e: Exception) {
        Log.e("Erro ao adicionar", "Msg: ${e.message}")
    }
}

suspend fun buscarPesquisas(pesquisaDao: PesquisaDAO): List<ItemPesquisa> {
    try {
        return pesquisaDao.buscarItens()
    } catch (e: Exception) {
        Log.e("Erro ao buscar", "Msg: ${e.message}")
        return emptyList()
    }
}

suspend fun deletarPesquisa(item: ItemPesquisa, pesquisaDao: PesquisaDAO) {
    try {
        pesquisaDao.removerItem(item)
    } catch (e: Exception) {
        Log.e("Erro ao deletar", "Msg: ${e.message}")
    }
}

suspend fun inserirFavorito(item: ItemFavorito, favoritoDao: FavoritoDao) {
    try {
        favoritoDao.inserir(item)
    } catch (e: Exception) {
        Log.e("Erro ao adicionar favorito", "Msg: ${e.message}")
    }
}

suspend fun buscarFavoritos(favoritoDao: FavoritoDao): List<ItemFavorito> {
    try {
        return favoritoDao.buscarFavoritos()
    } catch (e: Exception) {
        Log.e("Erro ao buscar favoritos", "Msg: ${e.message}")
        return emptyList()
    }
}

suspend fun atualizarFavorito(item: ItemFavorito, favoritoDao: FavoritoDao) {
    try {
        favoritoDao.atualizarFavorito(item)
    } catch (e: Exception) {
        Log.e("Erro ao atualizar favorito", "Msg: ${e.message}")
    }
}

suspend fun deletarFavorito(item: ItemFavorito, favoritoDao: FavoritoDao) {
    try {
        favoritoDao.removerFavorito(item)
    } catch (e: Exception) {
        Log.e("Erro ao deletar favorito", "Msg: ${e.message}")
    }
}