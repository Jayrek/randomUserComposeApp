package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun GridTileUserItem(
    icon: String,
    name: String,
    userName: String,
    email: String,
    address: String,
    nationality: String,
    onTap: () -> Unit
) {
    RandomUserCard(
        modifier = Modifier
            .padding(all = 2.dp)
            .fillMaxWidth(),
        onTap = onTap,
    ) {
        Column(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedUserIcon(icon = icon, nationality)
            RandomUserText(
                label = name,
                fontSize = if (name.length >= 12) 15 else 18,
                fontWeight = FontWeight.Bold
            )
            RandomUserText(
                label = userName,
                fontSize = 13,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
            RandomUserText(
                label = email,
                fontSize = 13,
                color = Color.Gray
            )
            RandomUserText(
                label = address,
                fontSize = 13,
                color = Color.Gray
            )
        }
    }
}
