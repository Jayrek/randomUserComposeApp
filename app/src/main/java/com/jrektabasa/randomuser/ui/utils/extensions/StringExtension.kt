package com.jrektabasa.randomuser.ui.utils.extensions

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.formatDate(): String {
    val formatter = DateTimeFormatter.ofPattern(
        "MMM dd, yyyy"
    )
    return try {
        formatter.format(OffsetDateTime.parse(this))
    } catch (e: DateTimeParseException) {
        "Invalid Date"
    }
}