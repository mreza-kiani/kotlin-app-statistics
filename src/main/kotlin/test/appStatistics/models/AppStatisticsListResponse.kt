package test.appStatistics.models

import java.io.Serializable

class AppStatisticsListResponse(val stats: List<AppStatisticsModel>) : Serializable {
}