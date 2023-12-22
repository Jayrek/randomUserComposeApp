package com.jrektabasa.randomuser.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.ui.components.RandomUserList
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    getUserByCountViewModel: GetUserByCountViewModel,
    navHostController: NavHostController,
    count: Int,
    natList: String,
) {
    Log.d("count", "UserListScreen: $count")
    var ascendingOrder by remember { mutableStateOf(true) }
    var isList by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RandomUser", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    ActionIconButton(
                        painter = painterResource(
                            id = R.drawable.sort_by_alpha_24
                        ),
                        description = "sort",
                    ) {
                        ascendingOrder = !ascendingOrder
                    }
                    ActionIconButton(
                        painter = if (isList) painterResource(
                            id = R.drawable.list_view_24
                        )
                        else painterResource(
                            id = R.drawable.grid_view_24
                        ),
                        description = "view",
                    ) {
                        isList = !isList
                    }

                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                RandomUserList(
                    getUserByCountViewModel = getUserByCountViewModel,
                    count = count,
                    natList = natList,
                    ascendingOrder = ascendingOrder,
                    isList = isList
                )
            }
        }
    )
}

@Composable
fun ActionIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    description: String = "icon",
    onToggle: () -> Unit
) {
    IconButton(
        onClick = onToggle,
        modifier = modifier
    ) {
        Icon(
            painter = painter,
            contentDescription = description,
            tint = Color.White
        )
    }
}
