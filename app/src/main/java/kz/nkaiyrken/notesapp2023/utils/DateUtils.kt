package kz.nkaiyrken.notesapp2023.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    // Function to get the formatted date
    fun getFormattedDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMM.", Locale("ru", "RU")) // Setting Locale for Russian language
        return dateFormat.format(currentDate)
    }
}