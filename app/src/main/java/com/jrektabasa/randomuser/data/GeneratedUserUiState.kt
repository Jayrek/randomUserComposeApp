package com.jrektabasa.randomuser.data

/**
 * Data class that represent the state of the generated user
 * **/
data class GeneratedUserUiState(
    /** count of users generated (10, 20, 50, 100)*/
    val userGenerateCount: Int = 0,
    /** concatenated list of nationalities of users*/
    val nationalities: String = "",
)
