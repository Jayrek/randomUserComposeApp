package com.jrektabasa.randomuser.data

/**
 * Data class that represent the state of the generated user
 * **/
data class GeneratedUserUiState(
    /** count of users generated (5, 10, 15, 20, 30, 50, 100)*/
    val userGenerateCount: Int = 5,
    /** concatenated list of nationalities of users*/
    val nationalities: String = "",
)
