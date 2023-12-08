package com.jrektabasa.randomuser.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun RandomUserNameText(name: String) {
    Text(
        text = name,
        fontSize = if (name.length >= 12) 15.sp else 18.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun RandomUserEmailText(email: String) {
    Text(
        text = email,
        fontSize = 15.sp,
        color = Color.Gray,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}