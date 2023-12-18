package com.jrektabasa.randomuser.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.model.Nationality
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.model.nationalities
import com.jrektabasa.randomuser.model.userCountList
import com.jrektabasa.randomuser.ui.components.RandomUserText
import com.jrektabasa.randomuser.ui.components.RoundedUserIcon
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@Composable
fun RandomUserApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable(
            route = "dashboard",
        ) {
            DashboardScreen(onGenerateUserClicked = { navController.navigate("user") })
        }
        composable(
            route = "user",
            arguments = listOf(navArgument("count") { defaultValue = "2" })
        ) { backStackEntry ->
            val count = backStackEntry.arguments?.getString("count")
            Log.d("count", "RandomUserApp: $count")
            count?.toInt()?.let { userCount -> UserListScreen(count = userCount) }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: GetUserByCountViewModel = hiltViewModel(),
    onGenerateUserClicked: () -> Unit
) {

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Random User Generator", color = Color.White)
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
                viewModel.getUserByCount()
            }
        })
    }) {

        Box(modifier = Modifier.padding(it)) {
            Column {
                DashboardUserPanel(viewModel = viewModel)
                GenerateUserPanel(onGenerateUser = onGenerateUserClicked)
            }
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
            modifier = Modifier.padding(5.dp),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp)
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
                                icon = user[0].picture.large, size = 120
                            )
                        }
                    }
                    RandomUserText(
                        label = "Hi, my name is", color = Color.Gray
                    )
                    RandomUserText(
                        label = "${user[0].name.first} ${user[0].name.last}",
                        fontSize = 20,
                        fontWeight = FontWeight.Bold
                    )
                    UserInfoRow(
                        image = Icons.Default.Email, label = user[0].email
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

@Composable
fun GenerateUserPanel(
    onGenerateUser: () -> Unit
) {
    var count by remember { mutableIntStateOf(10) }
//    var nationalityList = remember { mutableListOf<Nationality>() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            UserCountDropDown(onCountChange = { count = it })
            UserNationalityRadioButton(nationalities = nationalities) { option ->
                option.isSelected = !option.isSelected
                Log.d("updatedOnCheckedChange", "updatedOnCheckedChange: $option")
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            CustomButton(
                count,
                nationalities,
                onGenerateClicked = onGenerateUser
            )
        }
    }
}

@Composable
fun CheckBoxDemo() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCountDropDown(onCountChange: (Int) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("by 10") }
//    var count by remember { mutableIntStateOf(10) }
    Column {
        Text(
            text = "Generate users:", color = Color.Black, modifier = Modifier.padding(top = 10.dp)
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            modifier = Modifier.widthIn(
                min = 140.dp, max = 180.dp
            )
        ) {
            TextField(
                value = text,
                onValueChange = { },
                readOnly = true,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                userCountList.forEach { items ->
                    GenerateDropDownMenuItem(
                        label = items.label
                    ) {
                        isExpanded = false
                        text = items.label
                        onCountChange(items.count)
                    }
                }
            }
        }
    }
}

@Composable
fun GenerateDropDownMenuItem(
    label: String, onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Text(text = label) }
        }, onClick = onClick
    )
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

@Composable
fun UserNationalityRadioButton(
    nationalities: List<Nationality>, onOptionSelected: (Nationality) -> Unit
) {
    Column {
        Text(
            text = "Nationality:", color = Color.Black, modifier = Modifier.padding(top = 20.dp)
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

@Composable
fun CustomButton(
    count: Int, nat: List<Nationality>, onGenerateClicked: () -> Unit
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

