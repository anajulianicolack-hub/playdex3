package com.example.myapplication

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.util.Calendar


data class ReviewSalva(
    val nomeJogo: String,
    val status: String,
    val data: String,
    val nota: Int,
    val comentario: String
)


object ReviewsTemporarias {
    val reviews = mutableStateListOf<ReviewSalva>()
}


@Composable
fun TelaReview(
    nomeJogo: String,
    navController: NavHostController
) {
    val context = LocalContext.current

    val backgroundColor = Color(0xFF13082B)
    val roxoClaroHeader = Color(0xFF240047)
    val purpleCardColor = Color(0xFF8B2FC9)
    val darkInputColor = Color(0xFF090317)

    var selectedStatus by remember { mutableStateOf("Joguei") }
    var rating by remember { mutableStateOf(4) }
    var reviewText by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    val calendario = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, ano, mes, dia ->
            val diaFormatado = dia.toString().padStart(2, '0')
            val mesFormatado = (mes + 1).toString().padStart(2, '0')

            selectedDate = "$diaFormatado/$mesFormatado/$ano"
        },
        calendario.get(Calendar.YEAR),
        calendario.get(Calendar.MONTH),
        calendario.get(Calendar.DAY_OF_MONTH)
    )

    Scaffold(
        containerColor = backgroundColor
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HeaderReview(
                nomeJogo = nomeJogo,
                onBackClick = {
                    navController.popBackStack()
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                Text(
                    text = "Status",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    listOf("Joguei", "Quero Jogar", "Gostei").forEach { status ->

                        val isSelected = selectedStatus == status

                        Button(
                            onClick = {
                                selectedStatus = status
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSelected) {
                                    purpleCardColor
                                } else {
                                    Color(0xFF2A1C4E)
                                }
                            ),
                            shape = RoundedCornerShape(20.dp),
                            contentPadding = PaddingValues(
                                horizontal = 14.dp,
                                vertical = 4.dp
                            ),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Text(
                                text = status,
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Data",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = {
                        Text(
                            text = "Selecionar data",
                            color = Color.White
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                datePickerDialog.show()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Selecionar data",
                                tint = Color.White
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = purpleCardColor,
                        unfocusedContainerColor = purpleCardColor,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Sua Nota",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = if (i <= rating) {
                                Icons.Filled.Star
                            } else {
                                Icons.Outlined.Star
                            },
                            contentDescription = "Nota $i",
                            tint = if (i <= rating) {
                                Color(0xFFFFD700)
                            } else {
                                Color.White
                            },
                            modifier = Modifier
                                .size(32.dp)
                                .clickable {
                                    rating = i
                                }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Review ",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "(Opcional)",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = reviewText,
                    onValueChange = {
                        reviewText = it
                    },
                    placeholder = {
                        Text(
                            text = "Digite aqui...",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = darkInputColor,
                        unfocusedContainerColor = darkInputColor,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = purpleCardColor
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        if (selectedDate.isBlank()) {
                            Toast.makeText(
                                context,
                                "Selecione uma data.",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val novaReview = ReviewSalva(
                                nomeJogo = nomeJogo,
                                status = selectedStatus,
                                data = selectedDate,
                                nota = rating,
                                comentario = reviewText
                            )

                            ReviewsTemporarias.reviews.add(novaReview)

                            Toast.makeText(
                                context,
                                "Review de $nomeJogo salva com sucesso!",
                                Toast.LENGTH_SHORT
                            ).show()

                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.65f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = purpleCardColor
                    )
                ) {
                    Text(
                        text = "Salvar Review",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}


@Composable
fun HeaderReview(
    nomeJogo: String,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF240047),
                shape = RoundedCornerShape(
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .padding(vertical = 20.dp, horizontal = 24.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Registrar Review",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(100.dp, 130.dp)
            ) {
                CapaReview(nomeJogo = nomeJogo)
            }

            Spacer(modifier = Modifier.width(28.dp))

            Text(
                text = nomeJogo,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun CapaReview(nomeJogo: String) {

    val imagem = when (nomeJogo.lowercase()) {
        "resident evil" -> R.drawable.residentevil9
        "baldur's gate 3" -> R.drawable.galdursgate3
        "red dead ii" -> R.drawable.reddead2
        "red dead redemption ii" -> R.drawable.reddead2
        "stardew valley" -> R.drawable.stardewvalley
        "portal" -> R.drawable.portal
        "the witcher 3" -> R.drawable.thewitcher3
        else -> null
    }

    if (imagem != null) {
        Image(
            painter = painterResource(id = imagem),
            contentDescription = "Capa de $nomeJogo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF2A1C4E)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Capa de\n$nomeJogo",
                color = Color.White,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrarReviewTelaPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()

        TelaReview(
            nomeJogo = "Portal",
            navController = navController
        )
    }
}