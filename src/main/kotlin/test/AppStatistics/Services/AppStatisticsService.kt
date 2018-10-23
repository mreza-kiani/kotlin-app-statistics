package test.AppStatistics.Services

import com.ibm.icu.util.PersianCalendar
import test.AppStatistics.Models.AppStatisticsListResponse
import test.AppStatistics.Models.AppStatisticsModel
import test.AppStatistics.Repositories.AppStatisticsRepo
import java.util.*
import java.util.Calendar


class AppStatisticsService(private val appStatisticsRepo: AppStatisticsRepo) {

    fun getStats(startDate: Date, endDate: Date, type: Int): AppStatisticsListResponse {
        val appStatisticsList = appStatisticsRepo.findByTypeAndReportTimeBetween(type, startDate, endDate)
        val statisticsModels = mutableListOf<AppStatisticsModel>()

        appStatisticsList
                .forEach {
                    val cal = PersianCalendar.getInstance()
                    cal.time = it.reportTime
                    val year = cal.get(Calendar.YEAR)
                    val week = cal.get(Calendar.WEEK_OF_YEAR)

                    val model: AppStatisticsModel
                    model = try {
                        statisticsModels.first { it.year == year && it.weekNum == week }
                    } catch (e: NoSuchElementException) {
                        AppStatisticsModel(week, year)
                    }
                    model.addAppStatistics(it)
                    statisticsModels.add(model)
                }

        val sortedModels = statisticsModels.sortedWith(compareBy({ it.year }, { it.weekNum }))
        return AppStatisticsListResponse(sortedModels)
    }

}