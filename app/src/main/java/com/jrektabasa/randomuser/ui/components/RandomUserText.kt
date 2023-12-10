package com.jrektabasa.randomuser.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun RandomUserText(
    label: String,
    fontSize: Int = 15,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = label,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}
