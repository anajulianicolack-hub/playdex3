package com.example.myapplication

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDetalhesFavorito(
    nomeJogo: String,
    status: String,
    nota: Int,
    data: String,
    review: String,
    onBackClick: () -> Unit,
    onSalvar: (String, String, Int, String, String) -> Unit = { _, _, _, _, _ -> }
) {
    val context = LocalContext.current
    val campo = Color(0xFF090317)
    val roxo = Color(0xFF8B2FC9)

    var editando by remember { mutableStateOf(false) }

    var editNome by remember { mutableStateOf(nomeJogo) }
    var editStatus by remember { mutableStateOf(status) }
    var editNota by remember { mutableIntStateOf(nota) }
    var editData by remember { mutableStateOf(data) }
    var editReview by remember { mutableStateOf(review) }

    LaunchedEffect(nomeJogo, status, nota, data, review) {
        editNome = nomeJogo
        editStatus = status
        editNota = nota
        editData = data
        editReview = review
        editando = false
    }

    val calendario = Calendar.getInstance()
    val abrirCalendario = {
        DatePickerDialog(
            context,
            { _, ano, mes, dia ->
                editData = "%02d/%02d/%04d".format(dia, mes + 1, ano)
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Scaffold(
        containerColor = Color(0xFF13082B),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (editando) "Editar Favorito" else "Detalhes do Favorito",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (editando) editando = false
                            else onBackClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    if (editando) {
                        IconButton(onClick = {
                            if (editNome.isBlank() || editData.isBlank()) {
                                Toast.makeText(context, "Nome e data não podem ser vazios", Toast.LENGTH_SHORT).show()
                            } else {
                                onSalvar(editNome.trim(), editStatus, editNota, editData, editReview.trim())
                                editando = false
                            }
                        }) {
                            Icon(Icons.Default.Check, contentDescription = "Salvar", tint = Color(0xFF00E676))
                        }
                    } else {
                        IconButton(onClick = { editando = true }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.White)
                        }
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
                if (editando) {
                    Text("Nome do jogo", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = editNome,
                        onValueChange = { editNome = it },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = campo, unfocusedContainerColor = campo,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                            focusedBorderColor = roxo
                        )
                    )

                    Text("Status", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf("Joguei", "Quero jogar", "Gostei").forEach { opcao ->
                            Button(
                                onClick = { editStatus = opcao },
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (editStatus == opcao) roxo else Color(0xFF2A1C4E)
                                ),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text(opcao, fontSize = 11.sp, maxLines = 1)
                            }
                        }
                    }

                    Text("Data", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = editData,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = abrirCalendario) {
                                Icon(Icons.Default.DateRange, contentDescription = "Selecionar data", tint = Color.White)
                            }
                        },
                        modifier = Modifier.fillMaxWidth().clickable(onClick = abrirCalendario),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = campo, unfocusedContainerColor = campo,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White
                        )
                    )

                    Text("Sua Nota", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        for (estrela in 1..5) {
                            Icon(
                                imageVector = if (estrela <= editNota) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Nota $estrela",
                                tint = if (estrela <= editNota) Color(0xFFFFD700) else Color.LightGray,
                                modifier = Modifier.size(34.dp).clickable { editNota = estrela }
                            )
                        }
                    }

                    Text("Review", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    OutlinedTextField(
                        value = editReview,
                        onValueChange = { editReview = it },
                        modifier = Modifier.fillMaxWidth().height(140.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = campo, unfocusedContainerColor = campo,
                            focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                            focusedBorderColor = roxo
                        )
                    )
                } else {
                    Text(text = nomeJogo, color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.Bold)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFA855F7))
                        Spacer(Modifier.width(8.dp))
                        Text(text = "Favoritado", color = Color.White)
                    }

                    HorizontalDivider(color = Color(0xFF35234F))

                    Text(text = "Status", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Surface(color = Color(0xFF8B2FC9), shape = RoundedCornerShape(20.dp)) {
                        Text(text = status, color = Color.White, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                    }

                    Text(text = "Data", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = data, color = Color.LightGray)

                    Text(text = "Minha Nota", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "★".repeat(nota.coerceIn(0, 5)) + "☆".repeat(5 - nota.coerceIn(0, 5)),
                        color = Color(0xFFFFD700), fontSize = 28.sp
                    )

                    Text(text = "Minha Review", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Surface(modifier = Modifier.fillMaxWidth(), color = Color(0xFF1E103E), shape = RoundedCornerShape(16.dp)) {
                        Text(
                            text = review.ifBlank { "Nenhuma review foi escrita." },
                            color = Color.LightGray, fontSize = 14.sp, lineHeight = 22.sp, modifier = Modifier.padding(16.dp)
                        )
                    }
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