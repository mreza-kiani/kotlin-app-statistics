package test.appStatistics.models

class AppStatisticsModel (val weekNum: Int, val year: Int) {
    fun addAppStatistics(appStatistics: AppStatistics) {
        this.requests += appStatistics.requestCount()
        this.clicks += appStatistics.clicksCount()
        this.installs += appStatistics.installsCount()
    }

    var requests: Int = 0
    var clicks: Int = 0
    var installs: Int = 0
}