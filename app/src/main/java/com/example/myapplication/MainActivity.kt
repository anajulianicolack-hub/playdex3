package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backgroundColor = Color(0xFF13082B)

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            BottomNavBar(navInterno = navController)
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = RotasAbas.ABA_HOME,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(RotasAbas.ABA_HOME) {
                TelaHome(
                    onNavigateToSettings = {
                        navController.navigate(RotasAbas.TELACONFIGURACOES)
                    }
                )
            }

            composable(RotasAbas.ABA_PESQUISA) {
                TelaPesquisa(
                    onJogoClick = { nomeJogo ->
                        navController.navigate(
                            "${RotasAbas.TELA_DADOS_JOGO}/${Uri.encode(nomeJogo)}"
                        )
                    },
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = "${RotasAbas.TELA_DADOS_JOGO}/{nomeJogo}"
            ) { backStackEntry ->

                val nomeJogo = backStackEntry.arguments
                    ?.getString("nomeJogo") ?: ""

                TelaDadosJogo(
                    nomeJogo = nomeJogo,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onReviewClick = {
                        navController.navigate(
                            "${RotasAbas.TELAREVIEW}/${Uri.encode(nomeJogo)}"
                        )
                    }
                )
            }

            composable(
                route = "${RotasAbas.TELAREVIEW}/{nomeJogo}"
            ) { backStackEntry ->

                val nomeJogo = backStackEntry.arguments
                    ?.getString("nomeJogo") ?: ""

                TelaReview(
                    nomeJogo = nomeJogo,
                    navController = navController
                )
            }

            composable(RotasAbas.ABA_PERFIL) {
                TelaPerfilUsuario(
                    navController = navController,
                    onNavigateToSettings = {
                        navController.navigate(RotasAbas.TELACONFIGURACOES)
                    },
                    onNavigateToFavorites = {
                        navController.navigate(RotasAbas.TELAFAVORITOS)
                    }
                )
            }

            composable(RotasAbas.TELACONFIGURACOES) {
                TelaConfiguracoes(
                    navController = navController,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onNavigateToFavorites = {
                        navController.navigate(RotasAbas.TELAFAVORITOS)
                    }
                )
            }

            composable(RotasAbas.TELAFAVORITOS) {
                TelaFavoritos(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onFavoritoClick = { favorito ->
                        navController.navigate(
                            "${RotasAbas.TELA_DETALHES_FAVORITO}" +
                                    "?nome=${Uri.encode(favorito.title)}" +
                                    "&status=${Uri.encode(favorito.status)}" +
                                    "&nota=${favorito.nota}" +
                                    "&data=${Uri.encode(favorito.data)}" +
                                    "&review=${Uri.encode(favorito.review)}"
                        )
                    }
                )
            }

            composable(
                route = "${RotasAbas.TELA_DETALHES_FAVORITO}" +
                        "?nome={nome}&status={status}&nota={nota}" +
                        "&data={data}&review={review}",
                arguments = listOf(
                    navArgument("nome") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("status") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("nota") {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument("data") {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument("review") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) { backStackEntry ->

                val argumentos = backStackEntry.arguments

                TelaDetalhesFavorito(
                    nomeJogo = argumentos?.getString("nome") ?: "",
                    status = argumentos?.getString("status") ?: "",
                    nota = argumentos?.getInt("nota") ?: 0,
                    data = argumentos?.getString("data") ?: "",
                    review = argumentos?.getString("review") ?: "",
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}