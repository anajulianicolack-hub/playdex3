package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PesquisaViewModel(application: Application) : AndroidViewModel(application) {

    private val pesquisaDao = AppDatabase.getDatabase(application).pesquisaDao()

    var textoPesquisa by mutableStateOf("")
        private set

    val historico = mutableStateListOf<ItemPesquisa>()

    init {
        carregarHistorico()
    }

    private fun carregarHistorico() {
        viewModelScope.launch(Dispatchers.IO) {
            val itensDoBanco = pesquisaDao.buscarItens()
            withContext(Dispatchers.Main) {
                historico.clear()
                historico.addAll(itensDoBanco)
            }
        }
    }

    fun atualizarTexto(novoTexto: String) {
        textoPesquisa = novoTexto
    }

    fun adicionarPesquisa() {
        val pesquisaTrim = textoPesquisa.trim()
        if (pesquisaTrim.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                pesquisaDao.inserir(ItemPesquisa(id = 0, nome = pesquisaTrim))

                val itensAtualizados = pesquisaDao.buscarItens()
                withContext(Dispatchers.Main) {
                    historico.clear()
                    historico.addAll(itensAtualizados)
                    textoPesquisa = ""
                }
            }
        }
    }

    fun removerItem(item: ItemPesquisa) {
        viewModelScope.launch(Dispatchers.IO) {
            pesquisaDao.removerItem(item)

            val itensAtualizados = pesquisaDao.buscarItens()
            withContext(Dispatchers.Main) {
                historico.clear()
                historico.addAll(itensAtualizados)
            }
        }
    }
}