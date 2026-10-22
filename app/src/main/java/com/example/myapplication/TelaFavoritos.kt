package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

data class FavoritoItem(
    val id: Int,
    val title: String,
    val status: String,
    val review: String,
    val nota: Int,
    val data: String
)

@Composable
fun TelaFavoritos(
    onBackClick: () -> Unit = {},
    viewModel: FavoritosViewModel = viewModel()
) {
    val fundo = Color(0xFF13082B)
    val roxoHeader = Color(0xFF240047)
    val roxo = Color(0xFF8B2FC9)

    val favoritos = viewModel.favoritos
    val filtroSelecionado = viewModel.filtroSelecionado
    val adicionandoFavorito = viewModel.adicionandoFavorito
    val favoritoSelecionado = viewModel.favoritoSelecionado

    if (favoritoSelecionado != null) {
        val itemAtual = favoritoSelecionado!!

        TelaDetalhesFavorito(
            nomeJogo = itemAtual.title,
            status = itemAtual.status,
            nota = itemAtual.nota,
            data = itemAtual.data,
            review = itemAtual.review,
            onBackClick = { viewModel.selecionarFavorito(null) },
            onSalvar = { novoNome, novoStatus, novaNota, novaData, novaReview ->
                viewModel.atualizar(itemAtual, novoNome, novoStatus, novaNota, novaData, novaReview)
            }
        )
        return
    }

    if (adicionandoFavorito) {
        TelaAdicionarFavorito(
            onBackClick = { viewModel.fecharAdicionar() },
            onAdicionar = { nome, status, data, nota, review ->
                viewModel.adicionar(nome, status, data, nota, review)
            }
        )
        return
    }

    val filtros = listOf("Todos", "Joguei", "Quero jogar", "Gostei")

    val listaFiltrada = if (filtroSelecionado == "Todos") {
        favoritos.toList()
    } else {
        favoritos.filter { it.status.equals(filtroSelecionado, ignoreCase = true) }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(fundo)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(roxoHeader)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color.White)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Favoritos", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFA855F7))
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.abrirAdicionar() },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = roxo),
            shape = RoundedCornerShape(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Adicionar Favorito", fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(18.dp))

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("Filtros", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                items(filtros) { filtro ->
                    FilterChip(
                        selected = filtroSelecionado == filtro,
                        onClick = { viewModel.setFiltro(filtro) },
                        label = { Text(filtro, color = Color.White, fontSize = 13.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = roxo,
                            containerColor = Color(0xFF2A1C4E)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(listaFiltrada, key = { it.id }) { item ->
                CardFavoritoItem(
                    item = item,
                    onClick = { viewModel.selecionarFavorito(item) },
                    onRemover = { viewModel.remover(item) }
                )
            }
        }
    }
}

@Composable
fun CardFavoritoItem(
    item: FavoritoItem,
    onClick: () -> Unit,
    onRemover: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E103E)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Text(
                    text = item.title, color = Color.White, fontSize = 18.sp,
                    fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(color = Color(0xFF8B2FC9), shape = RoundedCornerShape(12.dp)) {
                        Text(
                            item.status, color = Color.White, fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Text(item.data, color = Color.LightGray, fontSize = 11.sp)
                }
                Text(
                    text = "★".repeat(item.nota.coerceIn(0, 5)) + "☆".repeat(5 - item.nota.coerceIn(0, 5)),
                    color = Color(0xFFFFD700), fontSize = 16.sp
                )
                Text("Review", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = item.review.ifBlank { "Sem review." }, color = Color.LightGray,
                    fontSize = 12.sp, maxLines = 2, overflow = TextOverflow.Ellipsis
                )
            }
            IconButton(onClick = onRemover) {
                Icon(
                    Icons.Default.Favorite, contentDescription = "Remover favorito",
                    tint = Color(0xFFA855F7), modifier = Modifier.size(26.dp)
                )
            }
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