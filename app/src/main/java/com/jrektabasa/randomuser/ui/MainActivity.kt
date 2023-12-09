package com.jrektabasa.randomuser.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.ui.components.ActionMenuItem
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
                var mDisplayMenu by remember { mutableStateOf(false) }
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
                                    mDisplayMenu = !mDisplayMenu
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                DropdownMenu(
                                    expanded = mDisplayMenu,
                                    onDismissRequest = { mDisplayMenu = false },
                                    modifier = Modifier
                                        .widthIn(min = 120.dp, max = 240.dp)
                                        .background(MaterialTheme.colorScheme.background)
                                ) {
                                    ActionMenuItem(
                                        label = "List view", onClick = {
                                            isList = true
                                            mDisplayMenu = false
                                        }
                                    )
                                    ActionMenuItem(
                                        label = "Grid view", onClick = {
                                            isList = false
                                            mDisplayMenu = false
                                        }
                                    )
                                    ActionMenuItem(
                                        label = "Sort by name", onClick = {
                                            mDisplayMenu = false
                                        }
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
                                RandomUserList(isList = isList)
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
    isList: Boolean = true
) {
    val user = viewModel.user.collectAsState()
    if (user.value != null) {
        val userResults: List<UserResult> = user.value!!.results
        if (isList)
            LazyColumn {
                itemsIndexed(items = userResults) { index, user ->
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
                itemsIndexed(items = userResults) { index, user ->
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