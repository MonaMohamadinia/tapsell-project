package ir.tapsell.web.controller

import ir.tapsell.repository.AppStatisticsRepository
import ir.tapsell.web.dto.AppStatisticsListResponse
import ir.tapsell.web.dto.AppStatisticsModel
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Provides a REST API to manage app statistics.
 *
 * @author Mona.
 */
@RestController
@RequestMapping("/apps")
class AppController(val repository: AppStatisticsRepository, val conversionService: ConversionService) {

    /**
     * Provides a weekly report about apps with the given [type] in a time span identified
     * by [startDate] and [endDate].
     */
    @GetMapping("{type}/stats")
    fun getState(@PathVariable type: Int,
                 @RequestParam startDate: Date,
                 @RequestParam endDate: Date): AppStatisticsListResponse {

        val sortByReportTime = Sort(Sort.Direction.ASC, "reportTime")
        val apps = repository.find(type, startDate, endDate, sortByReportTime)
        val map = apps.map { app -> conversionService.convert(app, AppStatisticsModel::class.java) }
                      .groupBy { app ->"${app.year}-${app.weekNum}" }
                      .map { app -> aggregate(app.value) }

        return AppStatisticsListResponse(map)
    }

    fun aggregate(group: List<AppStatisticsModel>): AppStatisticsModel {
        return AppStatisticsModel(
                group[0].weekNum,
                group[0].year,
                group.sumBy { app -> app.requests },
                group.sumBy { app -> app.clicks },
                group.sumBy { app -> app.installs }
        )
    }
}