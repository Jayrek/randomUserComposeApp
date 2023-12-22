package com.jrektabasa.randomuser.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import com.jrektabasa.randomuser.ui.utils.extensions.formatDate

@Composable
fun UserInfoPanel(
    viewModel: GetUserByCountViewModel
) {
    LaunchedEffect(true) {
        viewModel.getUserByCount()
    }

    val userState = viewModel.user.collectAsState()
    if (userState.value != null) {
        val user: List<UserResult> = userState.value!!.results
        Card(
            modifier = Modifier.padding(5.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserIconDashboard(
                        image = user[0].picture.large,
                        nationality = user[0].nat
                    )
                    RandomUserText(
                        label = "Hi, my name is", color = Color.Gray
                    )
                    RandomUserText(
                        label = "${user[0].name.first} ${user[0].name.last}",
                        fontSize = 18,
                        fontWeight = FontWeight.Bold
                    )
                    UserInfoRow(
                        image = Icons.Default.Email, label = user[0].email
                    )

                    UserInfoRow(
                        image = Icons.Default.DateRange,
                        label = user[0].dob.date.formatDate(),
                    )
                    UserInfoRow(
                        image = Icons.Default.LocationOn,
                        label = "${user[0].location.city}, ${user[0].location.country}",
                    )
                    UserInfoRow(
                        image = Icons.Default.Phone,
                        label = user[0].phone,
                    )
                }
            }
        }
    }
}

@Composable
fun UserIconDashboard(image: String, nationality: String) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(
                    top = 24.dp,
                    bottom = 20.dp
                )
        )
        Box(
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            RoundedUserIcon(
                icon = image,
                nationality = nationality,
                size = 100,
                natCircle = 30
            )
        }
    }
}


@Composable
fun UserInfoRow(
    image: ImageVector, label: String
) {
    Row {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = image,
            contentDescription = null,
            tint = Color.Gray
        )
        RandomUserText(
            label = label, color = Color.Gray
        )
    }
}