package com.jrektabasa.randomuser.model

data class UserCountItem(
    val label: String,
    val count: Int
)

val userCountList = listOf(
    UserCountItem("by 5", 5),
    UserCountItem("by 10", 10),
    UserCountItem("by 15", 15),
    UserCountItem("by 20", 20),
    UserCountItem("by 30", 30),
    UserCountItem("by 50", 50),
    UserCountItem("by 100", 100),
)