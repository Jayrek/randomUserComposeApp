package com.jrektabasa.randomuser.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import com.jrektabasa.randomuser.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val viewModel: GetUserByCountViewModel = viewModel()
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ProfileAccountScreen(
    viewModel: GetUserByCountViewModel = hiltViewModel()
) {
    val user = viewModel.user.collectAsState()
    Log.i("ProfileAccountScreen", "ProfileAccountScreen: ${user.value?.results?.get(0)?.email}")
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Text(text = "Email Address")
            EmailTextField()
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Password")
            PasswordTextField()
        }
//        Text(text = "Jayrek Tabasa")
//        Text(text = "Mobile Number: ")

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