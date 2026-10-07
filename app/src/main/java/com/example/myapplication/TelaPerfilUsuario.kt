package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

data class ProfileGameItem(val title: String, val imageResId: Int)

@Composable
fun TelaPerfilUsuario(
    navController: NavHostController = rememberNavController(),
    onNavigateToSettings: () -> Unit = {},
    onNavigateToFavorites: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF13082B))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF240047))
                .padding(horizontal = 16.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logoplaydex),
                contentDescription = "Logo PlayDex",
                modifier = Modifier
                    .width(130.dp)
                    .height(36.dp),
                contentScale = ContentScale.Fit
            )

            IconButton(
                onClick = onNavigateToSettings,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ProfileHeaderSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Seção Favoritos
        ProfileSectionTitle(
            title = "Favoritos",
            onSeeMoreClick = onNavigateToFavorites
        )
        ProfileGameList(
            games = listOf(
                ProfileGameItem("Resident Evil", R.drawable.residentevil9),
                ProfileGameItem("Baldur's Gate 3", R.drawable.galdursgate3),
                ProfileGameItem("Red Dead II", R.drawable.reddead2)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileSectionTitle(title = "Atividade recente")
        ProfileGameList(
            games = listOf(
                ProfileGameItem("Stardew Valley", R.drawable.stardewvalley),
                ProfileGameItem("Portal", R.drawable.portal),
                ProfileGameItem("The Witcher 3", R.drawable.thewitcher3)
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ProfileHeaderSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFF2A2040), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Avatar",
                tint = Color(0xFF5A4D73),
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "@noname",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Frase genérica para bio",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Nova Review",
                tint = Color(0xFFA855F7),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Fazer nova Review",
                color = Color(0xFFA855F7),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ProfileSectionTitle(
    title: String,
    onSeeMoreClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .then(
                if (onSeeMoreClick != null) Modifier.clickable { onSeeMoreClick() }
                else Modifier
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        if (onSeeMoreClick != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Ver mais",
                    color = Color(0xFFA855F7),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Ver mais",
                    tint = Color(0xFFA855F7),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileGameList(games: List<ProfileGameItem>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(games) { game ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(110.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp, 150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.DarkGray)
                ) {
                    Image(
                        painter = painterResource(id = game.imageResId),
                        contentDescription = game.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = game.title,
                    color = Color.White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilUsuarioPreview() {
    MyApplicationTheme {
        TelaPerfilUsuario()
    }
}