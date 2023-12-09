package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun RandomUserCard(
    modifier: Modifier,
    shape: Shape = RoundedCornerShape(5.dp),
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 5.dp
    ),
    onTap: () -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onTap),
        shape = shape,
        elevation = elevation,
    ) {
        Surface(
            modifier = modifier,
            content = content
        )
    }
}