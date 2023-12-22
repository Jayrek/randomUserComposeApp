package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun RoundedUserIcon(
    icon: String,
    nationality: String,
    size: Int = 70,
    natCircle: Int = 20,
) {

    Box {
        AsyncImage(
            model = icon,
            contentDescription = "user image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Gray,
                    shape = CircleShape
                )
                .background(MaterialTheme.colorScheme.primary)
        )
        Box(
            modifier = Modifier
                .size(natCircle.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = nationality,
                fontSize = 10.sp,
                color = White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
