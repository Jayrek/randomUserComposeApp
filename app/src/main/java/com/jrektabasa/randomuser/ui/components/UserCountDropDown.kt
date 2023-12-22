package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrektabasa.randomuser.model.userCountList
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCountDropDown(
    generateUserViewModel: GenerateUserViewModel,
    onCountChange: (Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(userCountList[0].label) }
    Column {
        RandomUserText(
            label = "Generate users:",
            fontSize = 13,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 10.dp)
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            modifier = Modifier.widthIn(
                min = 140.dp,
                max = 180.dp
            )
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { },
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center
                ),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }) {
                userCountList.forEach { items ->
                    GenerateDropDownMenuItem(
                        label = items.label
                    ) {
                        isExpanded = false
                        text = items.label
                        generateUserViewModel.setUserCount(items.count)
                        onCountChange(items.count)
                    }
                }
            }
        }
    }
}

@Composable
fun GenerateDropDownMenuItem(
    label: String,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text(text = label) }
        }, onClick = onClick
    )
}