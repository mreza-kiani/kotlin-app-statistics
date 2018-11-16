package test.appStatistics.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.appStatistics.models.AppStatistics
import test.appStatistics.models.AppStatisticsListResponse
import test.appStatistics.repositories.AppStatisticsRepo
import test.appStatistics.services.AppStatisticsService
import test.appStatistics.utils.toGregorian

@RestController
@RequestMapping("/app-statistics")
class AppStatisticsController(val appStatisticsRepo: AppStatisticsRepo) {

    @GetMapping("/all")
    fun all(): MutableList<AppStatistics> = this.appStatisticsRepo.findAll()

    @GetMapping("/report")
    fun query(@RequestBody request: ReportRequest): AppStatisticsListResponse {
        val appStatisticsService = AppStatisticsService(appStatisticsRepo)
        return appStatisticsService.getStats(
                request.startDate.toGregorian(),
                request.endDate.toGregorian(),
                request.type
        )
    }
}

data class ReportRequest(val type: Int, val startDate: String, val endDate: String)