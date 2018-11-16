package test.appStatistics.services

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import test.appStatistics.repositories.AppStatisticsRepo
import test.appStatistics.utils.toGregorian

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppStatisticsServiceTest {

    @Autowired
    private val repository: AppStatisticsRepo? = null

    lateinit var appStatisticsService: AppStatisticsService

    @Before
    fun setUp() {
        appStatisticsService = AppStatisticsService(this.repository!!)
    }

    @Test
    fun getStats() {
        val stats = appStatisticsService.getStats(
                "1397-01-01".toGregorian(), "1397-11-20".toGregorian(), 1
        )
        assertNotNull(stats)
    }
}