package test.appStatistics.utils

import com.ibm.icu.util.PersianCalendar
import java.util.*

fun Date.toPersianWeek(): Int {
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return cal.get(Calendar.WEEK_OF_YEAR)
}

fun Date.toPersianYear(): Int {
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return cal.get(Calendar.YEAR)
}