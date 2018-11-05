package test.appStatistics.services

import test.appStatistics.models.AppStatisticsListResponse
import test.appStatistics.models.AppStatisticsModel
import test.appStatistics.repositories.AppStatisticsRepo
import test.appStatistics.utils.toPersianWeek
import test.appStatistics.utils.toPersianYear
import java.util.*


class AppStatisticsService(private val appStatisticsRepo: AppStatisticsRepo) {

    fun getStats(startDate: Date, endDate: Date, type: Int): AppStatisticsListResponse {
        val appStatisticsList = appStatisticsRepo.findByTypeAndReportTimeBetween(type, startDate, endDate)
        val statisticsModels = appStatisticsList
                .groupingBy{ it.reportTime.toPersianWeek() }
                .aggregate { week, accumulator: AppStatisticsModel?, element, isFirst ->
                    if (isFirst) {
                        val year = element.reportTime.toPersianYear()
                        AppStatisticsModel(week, year).addAppStatistics(element)
                    }
                    else
                        accumulator!!.addAppStatistics(element)
                }
                .values
                .mapNotNull { it }
                .sortedWith(compareBy({ it.year }, { it.weekNum }))

        return AppStatisticsListResponse(statisticsModels)
    }

}