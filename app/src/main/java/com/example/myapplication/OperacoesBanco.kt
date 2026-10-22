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