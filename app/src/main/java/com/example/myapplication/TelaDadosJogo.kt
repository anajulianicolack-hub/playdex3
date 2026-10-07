package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val FundoPlayDex = Color(0xFF170C27)
private val RoxoPlayDex = Color(0xFFB83CFF)
private val TextoPlayDex = Color(0xFFE8E3ED)
private val TextoSecundario = Color(0xFFB8B0C4)

data class JogoDetalhe(
    val nome: String,
    val desenvolvedor: String,
    val genero: String,
    val resumo: String
)

val listaJogos = listOf(
    JogoDetalhe(
        nome = "Stardew Valley",
        desenvolvedor = "ConcernedApe",
        genero = "Simulação / RPG",
        resumo = "Stardew Valley é um jogo de simulação e RPG em que o jogador " +
                "herda uma antiga fazenda. É possível plantar, pescar, criar animais, " +
                "explorar cavernas e conhecer os moradores da cidade."
    ),

    JogoDetalhe(
        nome = "Cuphead",
        desenvolvedor = "Studio MDHR",
        genero = "Ação / Plataforma",
        resumo = "Cuphead é um jogo de ação e plataforma conhecido por seu visual " +
                "inspirado em desenhos animados antigos. O jogador enfrenta diversos " +
                "chefes e desafios que exigem habilidade e precisão."
    ),

    JogoDetalhe(
        nome = "Deltarune",
        desenvolvedor = "Toby Fox",
        genero = "RPG",
        resumo = "Deltarune é um RPG com exploração, personagens marcantes e combates " +
                "em turnos. O jogo apresenta uma história misteriosa e cheia de " +
                "diferentes personagens."
    ),

    JogoDetalhe(
        nome = "GTA VI",
        desenvolvedor = "Rockstar Games",
        genero = "Ação / Mundo Aberto",
        resumo = "GTA VI é um jogo de ação em mundo aberto com exploração, missões " +
                "e diversas atividades em um grande ambiente urbano."
    ),

    JogoDetalhe(
        nome = "Resident Evil",
        desenvolvedor = "Capcom",
        genero = "Terror / Sobrevivência",
        resumo = "Resident Evil é uma franquia de terror e sobrevivência conhecida " +
                "por seus ambientes sombrios, criaturas perigosas, exploração, combate " +
                "e resolução de enigmas."
    ),

    JogoDetalhe(
        nome = "Baldur's Gate 3",
        desenvolvedor = "Larian Studios",
        genero = "RPG",
        resumo = "Baldur's Gate 3 é um RPG com batalhas estratégicas, escolhas narrativas " +
                "e grande liberdade para o jogador. Suas decisões podem alterar diferentes " +
                "acontecimentos da aventura."
    ),

    JogoDetalhe(
        nome = "Red Dead Redemption II",
        desenvolvedor = "Rockstar Games",
        genero = "Ação / Aventura",
        resumo = "Red Dead Redemption II é um jogo de ação e aventura em mundo aberto " +
                "ambientado no velho oeste. O jogador acompanha uma história profunda " +
                "com exploração e diversas missões."
    ),

    JogoDetalhe(
        nome = "Portal",
        desenvolvedor = "Valve",
        genero = "Puzzle / Plataforma",
        resumo = "Portal é um jogo de puzzle em primeira pessoa em que o jogador utiliza " +
                "uma arma capaz de criar portais para resolver diferentes desafios."
    ),

    JogoDetalhe(
        nome = "The Witcher 3",
        desenvolvedor = "CD Projekt Red",
        genero = "RPG / Mundo Aberto",
        resumo = "The Witcher 3 é um RPG de mundo aberto que acompanha Geralt de Rívia " +
                "em uma jornada cheia de monstros, missões, escolhas difíceis e diferentes " +
                "lugares para explorar."
    )
)

fun buscarDadosJogo(nome: String): JogoDetalhe {
    return listaJogos.firstOrNull {
        it.nome.equals(nome, ignoreCase = true)
    } ?: JogoDetalhe(
        nome = if (nome.isNotBlank()) nome else "Jogo desconhecido",
        desenvolvedor = "Desenvolvedor não informado",
        genero = "Gênero não informado",
        resumo = "Ainda não temos informações cadastradas sobre este jogo."
    )
}

@Composable
fun TelaDadosJogo(
    nomeJogo: String,
    onBackClick: () -> Unit,
    onReviewClick: () -> Unit
) {
    val jogo = buscarDadosJogo(nomeJogo)

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
                    .clickable {
                        onBackClick()
                    }
            )

            Spacer(modifier = Modifier.height(18.dp))

            DadosPrincipaisJogo(jogo = jogo)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onReviewClick,
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
                text = jogo.resumo,
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
                comentario = "Jogo top!",
                estrelas = 5
            )

            ItemReview(
                nome = "noname",
                comentario = "Não gostei muito.",
                estrelas = 2
            )

            ItemReview(
                nome = "pedrinGamer",
                comentario = "Um dos melhores jogos que já joguei!",
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
fun DadosPrincipaisJogo(jogo: JogoDetalhe) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = jogo.nome,
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Desenvolvido por ${jogo.desenvolvedor}",
                color = TextoSecundario,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = jogo.genero,
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
                text = "Capa de\n${jogo.nome}",
                color = TextoSecundario,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
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

@Preview(showBackground = true)
@Composable
fun PreviewTelaDadosJogo() {
    TelaDadosJogo(
        nomeJogo = "Cuphead",
        onBackClick = {},
        onReviewClick = {}
    )
}