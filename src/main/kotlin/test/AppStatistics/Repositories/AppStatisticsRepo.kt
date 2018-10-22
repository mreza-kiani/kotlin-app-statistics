package test.AppStatistics.Repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import test.AppStatistics.Models.AppStatistics
import java.util.*

@Repository
interface AppStatisticsRepo : MongoRepository<AppStatistics, String> {
    fun findByType(type: Int) : List<AppStatistics>

    fun findByReportTimeBetween(startDate: Date, endDate: Date): List<AppStatistics>

    fun findByTypeAndReportTimeBetween(type: Int, startDate: Date, endDate: Date) : List<AppStatistics>
}