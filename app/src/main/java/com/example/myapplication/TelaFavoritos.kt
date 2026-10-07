package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

data class FavoritoItem(
    val id: Int,
    val title: String,
    val status: String,
    val review: String,
    val imageResId: Int,
    var isFavorite: Boolean = true
)

@Composable
fun TelaFavoritos(navController: NavHostController = rememberNavController()) {
    val backgroundColor = Color(0xFF13082B)
    val roxoHeader = Color(0xFF240047)
    val purpleCardColor = Color(0xFF8B2FC9)

    // Lista de exemplo
    val listaOriginal = remember {
        mutableStateListOf(
            FavoritoItem(1, "Resident Evil", "Joguei", "Jogo incrível com muita tensão e mistério do início ao fim.", R.drawable.residentevil9),
            FavoritoItem(2, "Baldur's Gate 3", "Quero jogar", "Ansiosa para jogar assim que tiver tempo livre!", R.drawable.galdursgate3),
            FavoritoItem(3, "Red Dead II", "Gostei", "História emocionante e gráficos espetaculares.", R.drawable.reddead2),
            FavoritoItem(4, "Portal", "Joguei", "Puzzles excelentes com narrativa marcante.", R.drawable.portal)
        )
    }

    var filtroSelecionado by remember { mutableStateOf("Todos") }
    val opcoesFiltro = listOf("Todos", "Joguei", "Quero jogar", "Gostei")

    val listaFiltrada = if (filtroSelecionado == "Todos") {
        listaOriginal
    } else {
        listaOriginal.filter { it.status.equals(filtroSelecionado, ignoreCase = true) }
    }

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            botoesdenavegacaoFavoritos(navInterno = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Header Top Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(roxoHeader)
                    .padding(vertical = 14.dp, horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Favoritos",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Coração",
                        tint = Color(0xFFA855F7),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seção de Filtros
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = "Filtros",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(opcoesFiltro) { filtro ->
                        val isSelected = filtroSelecionado == filtro
                        FilterChip(
                            selected = isSelected,
                            onClick = { filtroSelecionado = filtro },
                            label = { Text(filtro, color = Color.White, fontSize = 13.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = purpleCardColor,
                                containerColor = Color(0xFF2A1C4E)
                            ),
                            shape = RoundedCornerShape(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de Cards dos Favoritos
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(listaFiltrada) { item ->
                    CardFavoritoItem(
                        item = item,
                        onFavoritoToggle = {
                            val index = listaOriginal.indexOfFirst { it.id == item.id }
                            if (index != -1) {
                                listaOriginal[index] = listaOriginal[index].copy(
                                    isFavorite = !listaOriginal[index].isFavorite
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CardFavoritoItem(
    item: FavoritoItem,
    onFavoritoToggle: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E103E)),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Capa do Jogo
            Box(
                modifier = Modifier
                    .width(85.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.DarkGray)
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Informações do Jogo
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = item.title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Tag de Status
                    Surface(
                        color = Color(0xFF8B2FC9),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = item.status,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                        )
                    }
                }

                // Review breve
                Column {
                    Text(
                        text = "Review",
                        color = Color.LightGray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.review,
                        color = Color.Gray,
                        fontSize = 11.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Ícone do Coração
            IconButton(
                onClick = onFavoritoToggle,
                modifier = Modifier.align(Alignment.Top)
            ) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (item.isFavorite) Color(0xFFA855F7) else Color.Gray,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
fun botoesdenavegacaoFavoritos(navInterno: NavHostController) {
    val backStackEntry by navInterno.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route ?: ""

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

@Preview(showBackground = true)
@Composable
fun TelaFavoritosPreview() {
    MyApplicationTheme {
        TelaFavoritos()
    }
}