package com.example.myapplication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

@Composable
fun RegistrarReviewTela(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current

    val backgroundColor = Color(0xFF13082B)
    val roxoClaroHeader = Color(0xFF240047)
    val purpleCardColor = Color(0xFF8B2FC9)
    val darkInputColor = Color(0xFF090317)

    var selectedStatus by remember { mutableStateOf("Joguei") }
    var rating by remember { mutableStateOf(4) }
    var reviewText by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }

    Scaffold(
        containerColor = backgroundColor,
        bottomBar = {
            botoesdenavegacao3(navInterno = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = roxoClaroHeader,
                        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                    )
                    .padding(vertical = 20.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Registrar Review",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.size(100.dp, 130.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.portal),
                            contentDescription = "Portal",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(28.dp))

                    Text(
                        text = "Portal",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Status
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Status",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("Joguei", "Quero Jogar", "Gostei").forEach { status ->
                            val isSelected = selectedStatus == status
                            Button(
                                onClick = { selectedStatus = status },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) purpleCardColor else Color(0xFF2A1C4E)
                                ),
                                shape = RoundedCornerShape(20.dp),
                                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                                modifier = Modifier.height(34.dp)
                            ) {
                                Text(text = status, color = Color.White, fontSize = 12.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Data
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Data",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = selectedDate,
                        onValueChange = { selectedDate = it },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Data",
                                tint = Color.White
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = purpleCardColor,
                            unfocusedContainerColor = purpleCardColor,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Sua Nota
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sua Nota",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        for (i in 1..5) {
                            Icon(
                                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Nota $i",
                                tint = if (i <= rating) Color(0xFFFFD700) else Color.White,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { rating = i }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.Bottom) {
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
                        onValueChange = { reviewText = it },
                        placeholder = { Text("Digite aqui...", color = Color.Gray, fontSize = 12.sp) },
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
                            unfocusedTextColor = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        Toast.makeText(context, "Review salva com sucesso!", Toast.LENGTH_SHORT).show()
                        // Volta para o perfil ou home após salvar
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = purpleCardColor)
                ) {
                    Text(
                        text = "Salvar Review",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun botoesdenavegacao3(navInterno: NavHostController) {
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
fun RegistrarReviewTelaPreview() {
    MyApplicationTheme {
        RegistrarReviewTela()
    }
}