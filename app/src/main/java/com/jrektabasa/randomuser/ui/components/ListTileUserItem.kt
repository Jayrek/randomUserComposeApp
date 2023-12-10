package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListTileUserItem(
    icon: String,
    name: String,
    email: String,
    onTap: () -> Unit
) {
    RandomUserCard(
        modifier = Modifier
            .padding(all = 2.dp)
            .fillMaxWidth(),
        onTap = onTap,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
        ) {
            RoundedUserIcon(icon = icon)
            Column(modifier = Modifier.padding(all = 20.dp)) {
                RandomUserText(
                    label = name,
                    fontSize = if (name.length >= 12) 15 else 18
                )
                RandomUserText(
                    label = email,
                    color = Color.Gray
                )
            }
        }
    }
}