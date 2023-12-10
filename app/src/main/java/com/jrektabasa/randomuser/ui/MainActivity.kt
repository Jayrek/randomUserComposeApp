package com.jrektabasa.randomuser.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jrektabasa.randomuser.R
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.ui.components.GridTileUserItem
import com.jrektabasa.randomuser.ui.components.ListTileUserItem
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import com.jrektabasa.randomuser.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
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
        }
    }
}

@Composable
fun RandomUserList(
    viewModel: GetUserByCountViewModel = hiltViewModel(),
    count: Int,
    ascendingOrder: Boolean = true,
    isList: Boolean = true
) {

    LaunchedEffect(true) {
        viewModel.getUserByCount(count)
    }

    val user = viewModel.user.collectAsState()
    if (user.value != null) {
        val userResults: List<UserResult> = user.value!!.results
        val sortedResult: List<UserResult> = if (ascendingOrder) {
            userResults.sortedBy { it.name.first }
        } else {
            userResults.sortedByDescending { it.name.first }
        }
        if (isList)
            LazyColumn {
                itemsIndexed(items = sortedResult) { index, user ->
                    ListTileUserItem(
                        icon = user.picture.medium,
                        name = "${user.name.first} ${user.name.last}",
                        email = user.email,
                    ) {
                        Log.d("ListTileUserItem", "$index. ${user.name.first}")
                    }
                }
            }
        else
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                itemsIndexed(items = sortedResult) { index, user ->
                    GridTileUserItem(
                        icon = user.picture.medium,
                        name = "${user.name.first} ${user.name.last}",
                        email = user.email
                    ) {
                        Log.d("ListTileUserItem", "$index. ${user.name.first}")
                    }
                }
            }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        ProfileAccountScreen()
    }
}