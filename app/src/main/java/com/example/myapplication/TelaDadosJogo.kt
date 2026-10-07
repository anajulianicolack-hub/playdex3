package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FundoPlayDex = Color(0xFF170C27)
private val RoxoPlayDex = Color(0xFFB83CFF)
private val TextoPlayDex = Color(0xFFE8E3ED)
private val TextoSecundario = Color(0xFFB8B0C4)

@Preview(showBackground = true)
@Composable
fun TelaDadosJogo() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoPlayDex)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(28.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Nome do Jogo",
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "AAAA feito por Empresa/Dev",
                        color = TextoSecundario,
                        fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = "Estilo do jogo",
                        color = TextoSecundario,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(width = 95.dp, height = 125.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF30213F)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Capa\ndo jogo",
                        color = TextoSecundario,
                        fontSize = 16.sp,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF30213F),
                        contentColor = RoxoPlayDex
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Fazer Review",
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Resumo do Jogo",
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Uma breve descrição sobre a história, " +
                        "a jogabilidade e o universo do jogo. " +
                        "Aqui ficará o resumo para que os jogadores " +
                        "possam conhecer um pouco mais sobre ele.",
                color = TextoSecundario,
                fontSize = 14.sp,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(22.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF594766))
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Reviews Populares",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            ItemReview(
                nome = "user123",
                comentario = "jogo top",
                estrelas = 5
            )

            ItemReview(
                nome = "noname",
                comentario = "jogo ruim",
                estrelas = 2
            )

            ItemReview(
                nome = "pedrinGamer",
                comentario = "melhor jogo que já joguei",
                estrelas = 4
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Todas as Reviews",
                color = TextoPlayDex,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 24.dp)
            )
        }
    }
}

@Composable
fun ItemReview(
    nome: String,
    comentario: String,
    estrelas: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF30213F)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil do usuário",
                    tint = TextoPlayDex,
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = nome,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = comentario,
                    color = TextoSecundario,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "★".repeat(estrelas) + "☆".repeat(5 - estrelas),
                color = RoxoPlayDex,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFF392A46))
        )
    }
}