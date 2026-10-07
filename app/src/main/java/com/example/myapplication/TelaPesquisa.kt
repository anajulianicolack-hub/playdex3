package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

data class HistoricoPesquisa(
    val termo: String
)

@Preview
@Composable
fun TelaPesquisa(
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit = { navController.popBackStack() },
    onNavigateToFavorites: () -> Unit = {}
) {

    var textoPesquisa by remember { mutableStateOf("") }

    val historico = remember {
        mutableStateListOf(
            HistoricoPesquisa("Stardew Vallet"),
            HistoricoPesquisa("Cuphead"),
            HistoricoPesquisa("Dealtarune"),
            HistoricoPesquisa("GTA VI")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF170C27))
    ) {

        item {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // SETA PARA VOLTAR
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable{onBackClick()}
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    // BARRA DE PESQUISA
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF0D0716))
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Pesquisar",
                            tint = Color(0xFFB8B0C4),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(6.dp)
                        )

                        BasicTextField(
                            value = textoPesquisa,
                            onValueChange = {
                                textoPesquisa = it
                            },
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                color = Color(0xFFD8D2DF),
                                fontSize = 12.sp
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            decorationBox = { campoTexto ->
                                if (textoPesquisa.isEmpty()) {
                                    Text(
                                        text = "Pesquisar...",
                                        color = Color(0xFFB8B0C4),
                                        fontSize = 12.sp
                                    )
                                }
                                campoTexto()
                            }
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(8.dp)
                    )

                    // PROCURAR
                    Text(
                        text = "Procurar",
                        color = Color(0xFFB83CFF),
                        fontSize = 12.sp,
                        modifier = Modifier.clickable {
                            val pesquisaTrim = textoPesquisa.trim()
                            if (pesquisaTrim.isNotEmpty()) {
                                historico.removeAll { it.termo.equals(pesquisaTrim, ignoreCase = true) }
                                historico.add(0, HistoricoPesquisa(pesquisaTrim))
                                textoPesquisa = ""
                            }
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )
            }
        }

        items(historico, key = { it.termo }) { item ->
            ItemHistorico(
                nome = item.termo,
                onRemover = {
                    historico.remove(item)
                }
            )
        }

        item {
            Spacer(
                modifier = Modifier.height(25.dp)
            )
        }
    }
}

@Composable
fun ItemHistorico(nome: String, onRemover: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 38.dp,
                vertical = 9.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF170C27)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "◷",
                color = Color(0xFFE8E3ED),
                fontSize = 23.sp,
                modifier = Modifier.size(23.dp)
            )

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Text(
                text = nome,
                color = Color(0xFFD8D2DF),
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remover",
                tint = Color(0xFFE8E3ED),
                modifier = Modifier
                    .size(22.dp)
                    .clickable {
                        onRemover()
                    }
            )
        }
    }
}