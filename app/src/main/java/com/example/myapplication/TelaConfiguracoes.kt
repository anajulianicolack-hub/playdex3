package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun TelaConfiguracoes(
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit = { navController.popBackStack() },
    onNavigateToFavorites: () -> Unit = {}
) {
    val backgroundColor = Color(0xFF13082B)
    val roxoHeader = Color(0xFF240047)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(roxoHeader)
                .padding(vertical = 14.dp, horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }

            Text(
                text = "Configurações",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color(0xFF2A2040), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Avatar",
                    tint = Color(0xFF5A4D73),
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "@username",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemOpcaoConfiguracao(
                icon = Icons.Default.Person,
                titulo = "Informações da Conta",
                onClick = { /* Ação futuramente */ }
            )

            ItemOpcaoConfiguracao(
                icon = Icons.Default.Notifications,
                titulo = "Notificações",
                onClick = { /* Ação futuramente */ }
            )

            ItemOpcaoConfiguracao(
                icon = Icons.Default.Favorite,
                titulo = "Favoritos",
                onClick = onNavigateToFavorites
            )

            Spacer(modifier = Modifier.height(20.dp))

            ItemOpcaoConfiguracao(
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                titulo = "Sair",
                corIcone = Color(0xFFFF5555),
                corTexto = Color(0xFFFF5555),
                onClick = { /* Ação para deslogar */ }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ItemOpcaoConfiguracao(
    icon: ImageVector,
    titulo: String,
    corIcone: Color = Color(0xFFA855F7),
    corTexto: Color = Color.White,
    onClick: () -> Unit
) {
    Surface(
        color = Color(0xFF1E103E),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = titulo,
                    tint = corIcone,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = titulo,
                    color = corTexto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Avançar",
                tint = Color.Gray,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaConfiguracoesPreview() {
    MyApplicationTheme {
        TelaConfiguracoes()
    }
}