package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    onNavigateToSettings = { navController.navigate(RotasAbas.TELACONFIGURACOES) }
                )
            }
            composable(RotasAbas.ABA_PESQUISA) {
                TelaPesquisa(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
            composable(RotasAbas.ABA_PERFIL) {
                TelaPerfilUsuario(
                    navController = navController,
                    onNavigateToSettings = { navController.navigate(RotasAbas.TELACONFIGURACOES) },
                    onNavigateToFavorites = { navController.navigate(RotasAbas.TELAFAVORITOS) }
                )
            }
            composable(RotasAbas.TELACONFIGURACOES) {
                TelaConfiguracoes(
                    navController = navController,
                    onBackClick = { navController.popBackStack() },
                    onNavigateToFavorites = { navController.navigate(RotasAbas.TELAFAVORITOS) }
                )
            }
            composable(RotasAbas.TELAFAVORITOS) {
                TelaFavoritos(navController = navController)
            }
        }
    }
}