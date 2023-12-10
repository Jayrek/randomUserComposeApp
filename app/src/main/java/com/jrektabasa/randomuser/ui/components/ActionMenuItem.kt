package com.jrektabasa.randomuser.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ActionMenuItem(label: String, isSelected: Boolean, onClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(
                text = label,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
            )
        },
        onClick = onClick
    )
}