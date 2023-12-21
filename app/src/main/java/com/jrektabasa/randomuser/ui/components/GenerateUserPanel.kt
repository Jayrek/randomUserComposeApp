package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jrektabasa.randomuser.model.nationalities
import com.jrektabasa.randomuser.model.userCountList
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel

@Composable
fun GenerateUserPanel(
    viewModel: GenerateUserViewModel,
    onGenerateUser: () -> Unit
) {
    var count by remember { mutableIntStateOf(userCountList[0].count) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            UserCountDropDown(
                generateUserViewModel = viewModel,
                onCountChange = { count = it })
            UserNationalityCheckBox(
                nationalities = nationalities
            ) { option ->
                option.isSelected = !option.isSelected
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            GenerateUserButton(
                onGenerateClicked = onGenerateUser
            )
        }
    }
}


@Composable
fun GenerateUserButton(
    onGenerateClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 40.dp),
            shape = RoundedCornerShape(5.dp),
            onClick = onGenerateClicked
        ) {
            Text(text = "Generate")
        }
    }
}

@Composable
fun CheckBoxDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
}