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
import androidx.compose.material.icons.filled.DateRange
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

@Composable
fun TelaAdicionarFavorito(
    onBackClick: () -> Unit,
    onAdicionar: (String, String, String, Int, String) -> Unit,
    onBuscarId: (Int) -> Unit = {},
    resultadoBusca: ItemFavorito? = null
) {
    val context = LocalContext.current
    val fundo = Color(0xFF13082B)
    val roxo = Color(0xFF8B2FC9)
    val campo = Color(0xFF090317)

    var nome by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Joguei") }
    var data by remember { mutableStateOf("") }
    var nota by remember { mutableIntStateOf(0) }
    var review by remember { mutableStateOf("") }
    var idBusca by remember { mutableStateOf("") }

    val calendario = Calendar.getInstance()

    val abrirCalendario = {
        DatePickerDialog(
            context,
            { _, ano, mes, dia ->
                data = "%02d/%02d/%04d".format(dia, mes + 1, ano)
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF240047))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }

            Text(
                "Adicionar Favorito",
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Buscar por ID", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)


            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = idBusca,
                    onValueChange = { idBusca = it },
                    placeholder = { Text("Ex: 1") },
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = campo,
                        unfocusedContainerColor = campo,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = roxo
                    )
                )

                Button(
                    onClick = {
                        val id = idBusca.toIntOrNull()
                        if (id != null) onBuscarId(id) else Toast.makeText(context, "Digite um ID válido", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = roxo),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Buscar", fontWeight = FontWeight.Bold)
                }
            }


            if (resultadoBusca != null) {
                Surface(
                    color = Color(0xFF2A1C4E),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text(
                        text = "Encontrado: ${resultadoBusca.title} (Status: ${resultadoBusca.status})",
                        color = Color.White,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                "Nome do jogo",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                placeholder = { Text("Ex: Minecraft") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = campo,
                    unfocusedContainerColor = campo,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = roxo
                )
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "Status",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                listOf("Joguei", "Quero jogar", "Gostei").forEach { opcao ->
                    Button(
                        onClick = { status = opcao },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            horizontal = 4.dp,
                            vertical = 6.dp
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (status == opcao) {
                                roxo
                            } else {
                                Color(0xFF2A1C4E)
                            }
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            opcao,
                            fontSize = 11.sp,
                            maxLines = 1
                        )
                    }
                }
            }

            Spacer(Modifier.height(6.dp))

            Text(
                "Data",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = data,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text("Selecionar data") },
                trailingIcon = {
                    IconButton(onClick = abrirCalendario) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Selecionar data",
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = abrirCalendario),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = campo,
                    unfocusedContainerColor = campo,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "Sua Nota",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (estrela in 1..5) {
                    Icon(
                        imageVector = if (estrela <= nota) {
                            Icons.Filled.Star
                        } else {
                            Icons.Outlined.Star
                        },
                        contentDescription = "Nota $estrela",
                        tint = if (estrela <= nota) {
                            Color(0xFFFFD700)
                        } else {
                            Color.LightGray
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { nota = estrela }
                    )
                }
            }

            Spacer(Modifier.height(6.dp))

            Text(
                "Review (opcional)",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = review,
                onValueChange = { review = it },
                placeholder = { Text("Escreva sua review...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = campo,
                    unfocusedContainerColor = campo,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = roxo
                )
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    when {
                        nome.isBlank() -> Toast.makeText(
                            context,
                            "Digite o nome do jogo.",
                            Toast.LENGTH_SHORT
                        ).show()

                        data.isBlank() -> Toast.makeText(
                            context,
                            "Selecione uma data.",
                            Toast.LENGTH_SHORT
                        ).show()

                        else -> onAdicionar(
                            nome.trim(),
                            status,
                            data,
                            nota,
                            review.trim()
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = roxo
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    "Adicionar aos Favoritos",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaAdicionarFavoritoPreview() {
    MyApplicationTheme {
        TelaAdicionarFavorito(
            onBackClick = {},
            onAdicionar = { _, _, _, _, _ -> }
        )
    }
}