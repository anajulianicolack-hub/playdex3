package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf

class FavoritosViewModel {
    private val _favoritos = mutableStateListOf(
        FavoritoItem(1, "Resident Evil", "Joguei", "Jogo incrível com muita tensão e mistério.", 5, "05/10/2026"),
        FavoritoItem(2, "Baldur's Gate 3", "Quero jogar", "Ansiosa para jogar assim que tiver tempo!", 4, "06/10/2026"),
        FavoritoItem(3, "Red Dead Redemption II", "Gostei", "História emocionante e gráficos espetaculares.", 5, "07/10/2026"),
        FavoritoItem(4, "Portal", "Joguei", "Puzzles excelentes e narrativa marcante.", 4, "08/10/2026")
    )
    val favoritos: List<FavoritoItem> get() = _favoritos

    // Função para adicionar novo item
    fun adicionar(nome: String, status: String, data: String, nota: Int, review: String) {
        val novoId = (_favoritos.maxOfOrNull { it.id } ?: 0) + 1
        _favoritos.add(FavoritoItem(novoId, nome, status, review, nota, data))
    }

    // Função para remover item
    fun remover(item: FavoritoItem) {
        _favoritos.remove(item)
    }
}