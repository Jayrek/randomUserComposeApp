package com.jrektabasa.randomuser.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.ui.components.GenerateUserPanel
import com.jrektabasa.randomuser.ui.components.UserInfoPanel
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    getUserByCountViewModel: GetUserByCountViewModel,
    generateUserViewModel: GenerateUserViewModel,
    onGenerateUserClicked: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Random User Generator",
                color = Color.White
            )
        }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ), actions = {
            ActionIconButton(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(25.dp),
                painter = painterResource(
                    id = R.drawable.refresh_48
                ),
                description = "refresh",
            ) {
                getUserByCountViewModel.getUserByCount()
            }
        })
    }) {

        Box(modifier = Modifier.padding(it)) {
            Column {
                UserInfoPanel(viewModel = getUserByCountViewModel)
                GenerateUserPanel(
                    viewModel = generateUserViewModel,
                    onGenerateUser = onGenerateUserClicked
                )
            }
        }
    }
}
