package com.example.myapplication

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritosViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val favoritoDao = db.favoritoDao()

    var favoritos by mutableStateOf<List<ItemFavorito>>(emptyList())
        private set

    var filtroSelecionado by mutableStateOf("Todos")
        private set
    var adicionandoFavorito by mutableStateOf(false)
        private set
    var favoritoSelecionado by mutableStateOf<ItemFavorito?>(null)
        private set

    init {
        carregarFavoritos()
    }

    private fun carregarFavoritos() {
        viewModelScope.launch(Dispatchers.IO) {
            val lista = buscarFavoritos(favoritoDao)
            withContext(Dispatchers.Main) {
                favoritos = lista
            }
        }
    }

    fun setFiltro(filtro: String) {
        filtroSelecionado = filtro
    }

    fun abrirAdicionar() {
        adicionandoFavorito = true
    }

    fun fecharAdicionar() {
        adicionandoFavorito = false
    }

    fun selecionarFavorito(item: ItemFavorito?) {
        favoritoSelecionado = item
    }

    fun adicionar(nome: String, status: String, data: String, nota: Int, review: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val novoItem = ItemFavorito(title = nome, status = status, review = review, nota = nota, data = data)
            inserirFavorito(novoItem, favoritoDao)
            val listaAtualizada = buscarFavoritos(favoritoDao)
            withContext(Dispatchers.Main) {
                favoritos = listaAtualizada
                filtroSelecionado = "Todos"
                fecharAdicionar()
            }
        }
    }

    fun atualizar(itemAtual: ItemFavorito, novoNome: String, novoStatus: String, novaNota: Int, novaData: String, novaReview: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val itemAtualizado = itemAtual.copy(
                title = novoNome,
                status = novoStatus,
                nota = novaNota,
                data = novaData,
                review = novaReview
            )
            atualizarFavorito(itemAtualizado, favoritoDao)
            val listaAtualizada = buscarFavoritos(favoritoDao)
            withContext(Dispatchers.Main) {
                favoritos = listaAtualizada
                favoritoSelecionado = itemAtualizado
            }
        }
    }

    fun remover(item: ItemFavorito) {
        viewModelScope.launch(Dispatchers.IO) {
            deletarFavorito(item, favoritoDao)
            val listaAtualizada = buscarFavoritos(favoritoDao)
            withContext(Dispatchers.Main) {
                favoritos = listaAtualizada
                if (favoritoSelecionado?.id == item.id) {
                    favoritoSelecionado = null
                }
            }
        }
    }

    var resultadoBuscaId by mutableStateOf<ItemFavorito?>(null)
        private set

    fun buscarFavoritoPorId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultado = favoritoDao.buscarPorId(id)
            withContext(Dispatchers.Main) {
                resultadoBuscaId = resultado
            }
        }
    }

}