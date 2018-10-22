package test.AppStatistics.Controllers

import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import test.AppStatistics.Models.AppStatistics
import test.AppStatistics.Models.AppStatisticsListResponse
import test.AppStatistics.Repositories.AppStatisticsRepo
import test.AppStatistics.Services.AppStatisticsService
import java.util.*

@RestController
@RequestMapping("/app-statistics")
class AppStatisticsController(val appStatisticsRepo: AppStatisticsRepo) {

    @GetMapping("/all")
    fun all(): MutableList<AppStatistics> = this.appStatisticsRepo.findAll()

    @Cacheable(value = ["report"])
    @GetMapping("/report")
    fun query(): AppStatisticsListResponse {
        val type = (1..5).random()

        val startCalendar = GregorianCalendar(2017, 1, 1)
        val startDate = startCalendar.time

        val endCalendar = GregorianCalendar(2018, 6, 28)
        val endDate = endCalendar.time

        val appStatisticsService = AppStatisticsService(appStatisticsRepo)
        return appStatisticsService.getStats(startDate, endDate, type)
    }

    fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) +  start
}