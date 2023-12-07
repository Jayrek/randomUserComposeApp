package com.jrektabasa.randomuser.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.jrektabasa.randomuser.model.UserResult
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
                                            mDisplayMenu = true
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
                                ProfileAccountScreen(isList = isList)
                            }
                        }
                    }
                )

            }
        }
    }
}

@Composable
fun ActionMenuItem(label: String, onClick: () -> Unit) {
    val context = LocalContext.current
    DropdownMenuItem(text = { Text(text = label) }, onClick = {
        onClick()
        Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
    })
}

@Composable
fun ProfileAccountScreen(
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
                        leading = user.picture.medium,
                        title = "${user.name.first} ${user.name.last}",
                        subTitle = user.email,
                    ) {
                        Log.d("ListTileUserItem", "$index. ${user.name.first}")
                    }
                }
            }
        else
            LazyRow{
                itemsIndexed(items = userResults) { index, user ->
                   Text(text = "$index ${user.name.first}")
                }
            }
    }
}


@Composable
fun ListTileUserItem(
    leading: String,
    title: String,
    subTitle: String,
    onTap: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .padding(all = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onTap),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
        ) {
            AsyncImage(
                model = leading,
                contentDescription = "user image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = CircleShape
                    ),
            )
            Column(modifier = Modifier.padding(all = 20.dp)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = subTitle)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField() {
    var email by rememberSaveable { mutableStateOf("") }

    TextField(value = email, onValueChange = { email = it })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(value = password, onValueChange = { password = it })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
//        ProfileAccountScreen()
    }
}