package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDetalhesFavorito(
    nomeJogo: String,
    status: String,
    nota: Int,
    data: String,
    review: String,
    onBackClick: () -> Unit
) {
    Scaffold(
        containerColor = Color(0xFF13082B),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalhes do Favorito",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF240047)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF13082B))
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = nomeJogo,
                    color = Color.White,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color(0xFFA855F7)
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Favoritado",
                        color = Color.White
                    )
                }

                HorizontalDivider(
                    color = Color(0xFF35234F)
                )

                Text(
                    text = "Status",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    color = Color(0xFF8B2FC9),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = status,
                        color = Color.White,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )
                }

                Text(
                    text = "Data",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = data,
                    color = Color.LightGray
                )

                Text(
                    text = "Minha Nota",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "★".repeat(nota.coerceIn(0, 5)) +
                            "☆".repeat(5 - nota.coerceIn(0, 5)),
                    color = Color(0xFFFFD700),
                    fontSize = 28.sp
                )

                Text(
                    text = "Minha Review",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF1E103E),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = review.ifBlank {
                            "Nenhuma review foi escrita."
                        },
                        color = Color.LightGray,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaDetalhesFavoritoPreview() {
    MyApplicationTheme {
        TelaDetalhesFavorito(
            nomeJogo = "Minecraft",
            status = "Joguei",
            nota = 5,
            data = "08/10/2026",
            review = "Muito divertido!",
            onBackClick = {}
        )
    }
}