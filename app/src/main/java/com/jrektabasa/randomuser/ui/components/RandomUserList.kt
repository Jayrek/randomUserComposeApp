package com.jrektabasa.randomuser.ui.components

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.jrektabasa.randomuser.model.UserResult
import com.jrektabasa.randomuser.ui.screen.viewmodel.GetUserByCountViewModel

@Composable
fun RandomUserList(
    getUserByCountViewModel: GetUserByCountViewModel,
    count: Int,
    natList: String,
    ascendingOrder: Boolean = true,
    isList: Boolean = true
) {

    LaunchedEffect(true) {
        getUserByCountViewModel.getUserByCount(
            result = count,
            nat = natList
        )
    }

    val user = getUserByCountViewModel.user.collectAsState()
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
                        userName = user.login.username,
                        name = "${user.name.first} ${user.name.last}",
                        email = user.email,
                        address = "${user.location.city}, ${user.location.country}",
                        nationality = user.nat,
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
                        userName = user.login.username,
                        name = "${user.name.first} ${user.name.last}",
                        email = user.email,
                        address = "${user.location.city}, ${user.location.country}",
                        nationality = user.nat,
                    ) {
                        Log.d("ListTileUserItem", "$index. ${user.name.first}")
                    }
                }
            }
    }
}
