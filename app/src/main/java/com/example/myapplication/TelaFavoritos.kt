package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class FavoritoItem(
    val id: Int,
    val title: String,
    val status: String,
    val review: String,
    val imageResId: Int,
    var isFavorite: Boolean = true
)

@Preview
@Composable
fun TelaFavoritos (navController: NavHostController = rememberNavController()) {
    val backgroundColor = Color(0xFF13082B)
    val roxoHeader = Color(0xFF240047)
    val purpleCardColor = Color(0xFF8B2FC9)

    val listaOriginal = remember {
        mutableStateListOf(
            FavoritoItem(
                1,
                "Resident Evil",
                "Joguei",
                "Jogo incrível com muita tensão e mistério do início ao fim.",
                R.drawable.residentevil9
            ),
            FavoritoItem(
                2,
                "Baldur's Gate 3",
                "Quero jogar",
                "Ansiosa para jogar assim que tiver tempo livre!",
                R.drawable.galdursgate3
            ),
            FavoritoItem(
                3,
                "Red Dead II",
                "Gostei",
                "História emocionante e gráficos espetaculares.",
                R.drawable.reddead2
            ),
            FavoritoItem(
                4,
                "Portal",
                "Joguei",
                "Puzzles excelentes com narrativa marcante.",
                R.drawable.portal
            )
        )
    }
}
