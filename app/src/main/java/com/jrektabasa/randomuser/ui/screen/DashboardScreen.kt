package com.jrektabasa.randomuser.ui.screen

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.ui.components.RandomUserText
import com.jrektabasa.randomuser.ui.components.RoundedUserIcon
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: GetUserByCountViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Random User Generator", color = Color.White)
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    ActionIconButton(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(25.dp),
                        painter = painterResource(
                            id = R.drawable.refresh_48
                        ),
                        description = "refresh",
                    ) {
                        viewModel.getUserByCount()
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            DashboardUserPanel(viewModel = viewModel)
        }
    }
}

@Composable
fun DashboardUserPanel(
    viewModel: GetUserByCountViewModel
) {
    LaunchedEffect(true) {
        viewModel.getUserByCount()
    }

    val userState = viewModel.user.collectAsState()
    if (userState.value != null) {
        val user: List<UserResult> = userState.value!!.results
        Card(
            modifier = Modifier
                .padding(10.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = 20.dp,
                        bottom = 30.dp
                    )
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 24.dp)
                                .align(Alignment.Center),
                        )
                        Box(
                            modifier = Modifier.padding(vertical = 24.dp)
                        ) {
                            RoundedUserIcon(
                                icon = user[0].picture.large,
                                size = 120
                            )
                        }
                    }
                    RandomUserText(
                        label = "Hi, my name is",
                        color = Color.Gray
                    )
                    RandomUserText(
                        label = "${user[0].name.first} ${user[0].name.last}",
                        fontSize = 20,
                        fontWeight = FontWeight.Bold
                    )
                    UserInfoRow(
                        image = Icons.Default.Email,
                        label = user[0].email
                    )

                    UserInfoRow(
                        image = Icons.Default.DateRange,
                        label = formatDate(user[0].dob.date),
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

fun formatDate(date: String): String {
    val formatter = DateTimeFormatter.ofPattern(
        "MMM dd, yyyy"
    )
    return try {
        formatter.format(OffsetDateTime.parse(date))
    } catch (e: DateTimeParseException) {
        "Invalid Date"
    }
}

@Composable
fun UserInfoRow(
    image: ImageVector,
    label: String
) {
    Row {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = image,
            contentDescription = null,
            tint = Color.Gray
        )
        RandomUserText(
            label = label,
            color = Color.Gray
        )
    }
}
