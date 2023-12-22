package com.jrektabasa.randomuser.ui.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel
import com.jrektabasa.randomuser.ui.utils.Constants

@Composable
fun UserNationalityCheckBox(generateUserViewModel: GenerateUserViewModel) {

    var natListState by remember { mutableStateOf(emptyList<String>()) }

    Column {
        RandomUserText(
            label = "Nationality:",
            fontSize = 13,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 10.dp)
        )
        Box(
            modifier = Modifier.width(200.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.Center,
            ) {
                items(items = Constants.nationalities) { nat ->
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.Center)
                    ) {

                        Row {
                            Checkbox(
                                checked = natListState.contains(nat),
                                onCheckedChange = {
                                    natListState = if (natListState.contains(nat)) {
                                        natListState.filter { it != nat }
                                    } else {
                                        natListState + nat
                                    }
                                    val concatenatedList = natListState
                                        .joinToString(separator = ",")
                                        .lowercase()
                                    generateUserViewModel.setUserNationalities(concatenatedList)
                                },
                                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.primary),
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = nat,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 14.sp,
                                    color = Color.DarkGray
                                ),
                                modifier = Modifier.padding(top = 13.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}