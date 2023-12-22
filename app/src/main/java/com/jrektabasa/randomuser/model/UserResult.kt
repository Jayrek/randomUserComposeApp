package com.jrektabasa.randomuser.model

data class UserResult(
    val email: String,
    val cell: String,
    val dob: Dob,
//    @field:Json(name = "email") val email: String = "",
//    val gender: String,
//    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
//    val registered: Registered
)