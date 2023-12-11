package com.jrektabasa.randomuser.model

data class UserCountItem(
    val label: String,
    val count: Int
)

val userCountList = listOf(
    UserCountItem("by 10", 10),
    UserCountItem("by 20", 20),
    UserCountItem("by 50", 50),
    UserCountItem("by 100", 100)
)