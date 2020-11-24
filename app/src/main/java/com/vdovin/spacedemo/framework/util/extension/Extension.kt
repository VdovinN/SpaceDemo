package com.vdovin.spacedemo.framework.util.extension

import com.vdovin.spacedemo.framework.util.DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimestampToFormattedDate(): String {
    val timestamp = this
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    return formatter.format(date)
}