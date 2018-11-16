package test.appStatistics.controllers

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppStatisticsControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun failureReportAPI() {
        val result = testRestTemplate
                .getForEntity("/app-statistics/report", String::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
    }

    @Test
    fun successReportAPI() {
        val result = testRestTemplate
                .getForEntity(
                        "/app-statistics/report?type=1&startDate=1397-01-01&endDate=1397-11-29",
                        String::class.java
                )
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
    }
}