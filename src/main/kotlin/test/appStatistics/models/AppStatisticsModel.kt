package test.appStatistics.models

import java.io.Serializable

class AppStatisticsModel (val weekNum: Int, val year: Int) : Serializable {
    fun addAppStatistics(appStatistics: AppStatistics): AppStatisticsModel {
        this.requests += appStatistics.requestCount()
        this.clicks += appStatistics.clicksCount()
        this.installs += appStatistics.installsCount()
        return this
    }

    var requests: Int = 0
    var clicks: Int = 0
    var installs: Int = 0
}