package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RoundedUserIcon(
    icon: String,
    size: Int = 70
) {
    AsyncImage(
        model = icon,
        contentDescription = "user image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.primary)
    )
}