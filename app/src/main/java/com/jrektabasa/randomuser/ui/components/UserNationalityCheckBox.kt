package com.jrektabasa.randomuser.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrektabasa.randomuser.model.Nationality

@Composable
fun UserNationalityCheckBox(
    nationalities: List<Nationality>, onOptionSelected: (Nationality) -> Unit
) {
    Column {
        Text(
            text = "Nationality:",
            color = Color.DarkGray,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Box(
            modifier = Modifier.width(200.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.Center,
            ) {
                items(items = nationalities) { option ->
                    Log.d("TAG", "UserNationalityRadioButton: ${option.code}")
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Center)
                    ) {

                        Row {
                            Checkbox(
                                checked = option.isSelected,
                                onCheckedChange = { checked ->
                                    option.isSelected = checked
                                    onOptionSelected(option)
                                },
                                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = option.code,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }
                    }

                }
            }
        }
    }
}