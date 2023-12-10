package com.jrektabasa.randomuser.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.ui.components.RandomUserList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen() {
    var ascendingOrder by remember { mutableStateOf(true) }
    var isList by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RandomUser", color = Color.White) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        Log.d("TAG", "onCreate: $ascendingOrder")
                        ascendingOrder = !ascendingOrder
                    }) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.sort_by_alpha_24
                            ),
                            contentDescription = "sorting by name",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        isList = !isList
                    }) {
                        Icon(
                            painter = if (isList) painterResource(
                                id = R.drawable.list_view_24
                            )
                            else painterResource(
                                id = R.drawable.grid_view_24
                            ),
                            contentDescription = "display view",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RandomUserList(
                        count = 10,
                        ascendingOrder = ascendingOrder,
                        isList = isList
                    )
                }
            }
        }
    )
}
