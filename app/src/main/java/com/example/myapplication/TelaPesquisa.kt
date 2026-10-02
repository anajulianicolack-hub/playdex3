package com.example.myapplication

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun TelaPesquisa(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF170C27))
    ) {

        // ===============================
        // TOPO
        // ===============================

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
                modifier = Modifier.width(30.dp)
            )

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            // BARRA DE PESQUISA
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(
                        Color(0xFF0D0716)
                    )
                    .padding(
                        horizontal = 10.dp
                    ),
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

                Text(
                    text = "Pesquisar...",
                    color = Color(0xFFB8B0C4),
                    fontSize = 12.sp
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                // PROCURAR
                Text(
                    text = "Procurar",
                    color = Color(0xFFB83CFF),
                    fontSize = 12.sp
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ===========================
            // HISTÓRICO
            // ===========================

            ItemHistorico(
                nome = "Stardew Valley"
            )

            ItemHistorico(
                nome = "Cuphead"
            )

            ItemHistorico(
                nome = "Deltarune"
            )

            ItemHistorico(
                nome = "GTA VI"
            )


            Spacer(
                modifier = Modifier.height(25.dp)
            )

            // ===========================
            // VER MAIS
            // ===========================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ver mais",
                    color = Color.White,
                    fontSize = 13.sp
                )

                Text(
                    text = "⌄",
                    color = Color.White,
                    fontSize = 18.sp
                )

            }

        }

    }
}

@Composable
fun ItemHistorico(nome: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 38.dp,
                vertical = 9.dp
            ),

        verticalAlignment = Alignment.CenterVertically
    ) {

        // ÍCONE DE HISTÓRICO

        /*
        Icon(
            imageVector = Icons.Default.,
            contentDescription = "Histórico",
            tint = Color(0xFFE8E3ED),
            modifier = Modifier.size(23.dp)
        )*/


        Spacer(
            modifier = Modifier.width(14.dp)
        )


        // NOME DO JOGO

        Text(
            text = nome,
            color = Color(0xFFD8D2DF),
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )


        // X

        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Remover",
            tint = Color(0xFFE8E3ED),
            modifier = Modifier.size(22.dp)
        )
    }
}

