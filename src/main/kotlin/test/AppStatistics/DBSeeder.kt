package test.AppStatistics

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import test.AppStatistics.Models.AppStatistics
import test.AppStatistics.Repositories.AppStatisticsRepo
import java.util.*
import java.util.GregorianCalendar


@Component
class DBSeeder(val appStatisticsRepo: AppStatisticsRepo) : CommandLineRunner{

    override fun run(vararg args: String?) {
        this.appStatisticsRepo.deleteAll()

        val appStatisticsList = mutableListOf<AppStatistics>()

        for (i in 1..500) {
            val appStatistics = makeRandomAppStatistics()
            appStatisticsList.add(appStatistics)

            println("-- AppStatistics $appStatistics created!")
        }

        this.appStatisticsRepo.saveAll(appStatisticsList)

        println("-- AppStatistics list saved in database")
    }

    fun makeRandomAppStatistics(): AppStatistics {
        val year = (2016..2018).random()
        val month = (1..12).random()
        val day = (1..30).random()
        val myCalendar = GregorianCalendar(year, month, day)
        val myDate = myCalendar.time

        val type = (1..5).random()

        val videoRequests = (0..10).random()
        val videoClicks = (0..10).random()
        val videoInstalls = (0..10).random()

        val webViewRequests = (0..10).random()
        val webViewClicks = (0..10).random()
        val webViewInstalls = (0..10).random()

        return AppStatistics(
                myDate,
                type,
                videoRequests,
                videoClicks,
                videoInstalls,
                webViewRequests,
                webViewClicks,
                webViewInstalls
        )
    }

    fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) +  start
}