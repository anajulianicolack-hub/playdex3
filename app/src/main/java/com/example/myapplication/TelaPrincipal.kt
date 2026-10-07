package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

data class GameItem(val title: String, val imageResId: Int)
@Composable
fun TelaHome(onNavigateToSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopHeaderSection(onNavigateToSettings = onNavigateToSettings)

        Spacer(modifier = Modifier.height(16.dp))

        BannerSection()

        Spacer(modifier = Modifier.height(20.dp))

        titulosJogos(title = "Popular")
        listaJogos(
            games = listOf(
                GameItem("Resident Evil", R.drawable.residentevil9),
                GameItem("Baldur's Gate 3", R.drawable.galdursgate3),
                GameItem("Red Dead II", R.drawable.reddead2)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        titulosJogos(title = "Para Você")
        listaJogos(
            games = listOf(
                GameItem("Stardew Valley", R.drawable.stardewvalley),
                GameItem("Portal", R.drawable.portal),
                GameItem("The Witcher 3", R.drawable.thewitcher3)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun BottomNavBar(navInterno: NavHostController) {
    val backStackEntry by navInterno.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route ?: RotasAbas.ABA_HOME

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(Color(0xFF240047))
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (rotaAtual != RotasAbas.ABA_PESQUISA) {
                    navInterno.navigate(RotasAbas.ABA_PESQUISA) {
                        popUpTo(RotasAbas.ABA_HOME)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Pesquisa",
                tint = if (rotaAtual == RotasAbas.ABA_PESQUISA) Color(0xFFA855F7) else Color.White,
                modifier = Modifier.size(38.dp)
            )
        }

        IconButton(
            onClick = {
                if (rotaAtual != RotasAbas.ABA_HOME) {
                    navInterno.navigate(RotasAbas.ABA_HOME) {
                        popUpTo(RotasAbas.ABA_HOME) { inclusive = true }
                    }
                }
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.pladexsemfundo),
                contentDescription = "Home",
                modifier = Modifier.size(38.dp)
            )
        }

        IconButton(
            onClick = {
                if (rotaAtual != RotasAbas.ABA_PERFIL) {
                    navInterno.navigate(RotasAbas.ABA_PERFIL) {
                        popUpTo(RotasAbas.ABA_HOME)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Perfil",
                tint = if (rotaAtual == RotasAbas.ABA_PERFIL) Color(0xFFA855F7) else Color.White,
                modifier = Modifier.size(38.dp)
            )
        }
    }
}

@Composable
fun TopHeaderSection(onNavigateToSettings: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF240047))
            .padding(top = 16.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logoplaydex),
                contentDescription = "Logo PlayDex",
                modifier = Modifier.height(70.dp)
            )

            IconButton(
                onClick = onNavigateToSettings,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(1.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Games",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(4.dp)
                        .background(
                            color = Color(0xFFA855F7),
                            shape = RoundedCornerShape(50)
                        )
                )
            }

            Text(
                text = "Feed",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "News",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BannerSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(180.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.DarkGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.telanoticias),
                contentDescription = "Banner Principal",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(modifier = Modifier.size(8.dp).background(Color(0xFFA855F7), CircleShape))
            Box(modifier = Modifier.size(8.dp).background(Color.Gray, CircleShape))
            Box(modifier = Modifier.size(8.dp).background(Color.Gray, CircleShape))
            Box(modifier = Modifier.size(8.dp).background(Color.Gray, CircleShape))
        }
    }
}

@Composable
fun titulosJogos(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun listaJogos(games: List<GameItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(110.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp, 150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.DarkGray)
                ) {
                    Image(
                        painter = painterResource(id = game.imageResId),
                        contentDescription = game.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = game.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayDexPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}