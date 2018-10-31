package test.appStatistics.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "appStatistics")
class AppStatistics
(
        var reportTime: Date,
        val type: Int,

        private val videoRequests: Int,
        private val videoClicks: Int,
        private val videoInstalls: Int,

        private val webViewRequests: Int,
        private val webViewClicks: Int,
        private val webViewInstalls: Int
)
{
    fun requestCount(): Int = videoRequests + webViewRequests
    fun clicksCount(): Int = videoClicks + webViewClicks
    fun installsCount(): Int = videoInstalls + webViewInstalls

    @Id
    var id: String? = null
}