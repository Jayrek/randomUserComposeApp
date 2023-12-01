package com.jrektabasa.randomuser.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileAccountScreen()
                }
            }
        }
    }
}

@Composable
fun ProfileAccountScreen(
    viewModel: GetUserByCountViewModel = hiltViewModel()
) {
    val user = viewModel.user.collectAsState()
    if (user.value != null) {
        val userResults: List<UserResult> = user.value!!.results
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