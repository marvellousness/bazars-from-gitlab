package tungp.android.bazarbooks.data.local.database

import android.icu.util.Calendar
import java.util.Date

// Helper function to create dates using Calendar
fun createDate(year: Int, month: Int, day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day) // Calendar months are 0-indexed (January is 0)
    // Set time to midnight to be consistent if time is not relevant
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}