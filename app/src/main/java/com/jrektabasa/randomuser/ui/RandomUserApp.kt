package com.jrektabasa.randomuser.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jrektabasa.randomuser.ui.screen.DashboardScreen
import com.jrektabasa.randomuser.ui.screen.UserListScreen
import com.jrektabasa.randomuser.ui.screen.viewmodel.GenerateUserViewModel
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel
import com.jrektabasa.randomuser.ui.utils.Screens

@Composable
fun RandomUserApp(
    generateUserViewModel: GenerateUserViewModel = hiltViewModel(),
    getUserByCountViewModel: GetUserByCountViewModel = hiltViewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val uiState by generateUserViewModel.uiState.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = Screens.Dashboard.name
    ) {
        composable(
            route = Screens.Dashboard.name,
        ) {
            DashboardScreen(
                getUserByCountViewModel = getUserByCountViewModel,
                generateUserViewModel = generateUserViewModel,
                onGenerateUserClicked = { navHostController.navigate(Screens.UserList.name) }
            )
        }
        composable(
            route = Screens.UserList.name,
        ) {
            UserListScreen(
                getUserByCountViewModel = getUserByCountViewModel,
                navHostController = navHostController,
                count = uiState.userGenerateCount
            )
        }
    }

}