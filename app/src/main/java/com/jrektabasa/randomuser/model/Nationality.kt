package com.jrektabasa.randomuser.model

data class Nationality(
    val name: String,
    val code: String,
    var isSelected: Boolean
)

var nationalities = listOf(
    Nationality("Australia", "AU", false),
    Nationality("Canada", "CA", false),
    Nationality("Spain", "ES", false),
    Nationality("France", "FR", false),
)

//AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IN, IR, MX, NL, NO, NZ, RS, TR, UA, US