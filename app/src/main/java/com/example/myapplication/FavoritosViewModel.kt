package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FavoritosViewModel : ViewModel() {
    private val _favoritos = mutableStateListOf(
        FavoritoItem(1, "Resident Evil", "Joguei", "Jogo incrível com muita tensão e mistério.", 5, "05/10/2026"),
        FavoritoItem(2, "Baldur's Gate 3", "Quero jogar", "Ansiosa para jogar assim que tiver tempo!", 4, "06/10/2026"),
        FavoritoItem(3, "Red Dead Redemption II", "Gostei", "História emocionante e gráficos espetaculares.", 5, "07/10/2026"),
        FavoritoItem(4, "Portal", "Joguei", "Puzzles excelentes e narrativa marcante.", 4, "08/10/2026")
    )
    val favoritos: List<FavoritoItem> get() = _favoritos
    var filtroSelecionado by mutableStateOf("Todos")
        private set
    var adicionandoFavorito by mutableStateOf(false)
        private set
    var favoritoSelecionado by mutableStateOf<FavoritoItem?>(null)
        private set
    fun setFiltro(filtro: String) {
        filtroSelecionado = filtro
    }
    fun abrirAdicionar() {
        adicionandoFavorito = true
    }
    fun fecharAdicionar() {
        adicionandoFavorito = false
    }
    fun selecionarFavorito(item: FavoritoItem?) {
        favoritoSelecionado = item
    }

    fun adicionar(nome: String, status: String, data: String, nota: Int, review: String) {
        val novoId = (_favoritos.maxOfOrNull { it.id } ?: 0) + 1
        _favoritos.add(
            FavoritoItem(id = novoId, title = nome, status = status, review = review, nota = nota, data = data)
        )
        filtroSelecionado = "Todos"
        fecharAdicionar()
    }

    fun atualizar(itemAtual: FavoritoItem, novoNome: String, novoStatus: String, novaNota: Int, novaData: String, novaReview: String) {
        val index = _favoritos.indexOfFirst { it.id == itemAtual.id }
        if (index != -1) {
            val itemAtualizado = _favoritos[index].copy(
                title = novoNome,
                status = novoStatus,
                nota = novaNota,
                data = novaData,
                review = novaReview
            )
            _favoritos[index] = itemAtualizado
            favoritoSelecionado = itemAtualizado
        }
    }

    fun remover(item: FavoritoItem) {
        _favoritos.remove(item)
        if (favoritoSelecionado?.id == item.id) {
            favoritoSelecionado = null
        }
    }
}