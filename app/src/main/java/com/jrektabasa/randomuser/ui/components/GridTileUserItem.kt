package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GridTileUserItem(
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
        Column(
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RoundedUserIcon(icon = icon)
            RandomUserNameText(name = name)
            RandomUserEmailText(email = email)
        }
    }
}
