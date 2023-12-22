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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jrektabasa.randomuser.model.userCountList
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel

/**
 * Renders a panel with a drop-down selection for the number of users to generate,
 * a set of checkboxes for selecting nationalities, and
 * a button to generate the users.
 *
 * @param generateUserViewModel the view model object for the UI component.
 * @param onGenerateUser the function to call when the generate button is clicked.
 */
@Composable
fun GenerateUserPanel(
    generateUserViewModel: GenerateUserViewModel,
    initialCount: Int,
    onGenerateUser: () -> Unit
) {

    var count by remember { mutableIntStateOf(initialCount) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {

            val countIndex = userCountList.indexOfFirst { it.count == count }

            /** drop down selection to generate users by count*/
            UserCountDropDown(
                generateUserViewModel = generateUserViewModel, count = countIndex
            ) { count = it }


            /** nationality checkbox*/
            UserNationalityCheckBox(generateUserViewModel = generateUserViewModel)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            /** generate button*/
            GenerateUserButton { onGenerateUser() }
        }
    }
}

@Composable
fun GenerateUserButton(onGenerateClicked: () -> Unit) {
    Box(contentAlignment = Alignment.Center) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 30.dp),
            shape = RoundedCornerShape(5.dp),
            onClick = onGenerateClicked
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RandomUserText(
                    label = "Hit Me!",
                    fontSize = 25,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                RandomUserText(
                    label = "to generate...",
                    fontSize = 13,
                    color = Color.White,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}