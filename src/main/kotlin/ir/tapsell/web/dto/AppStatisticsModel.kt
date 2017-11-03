package ir.tapsell.web.dto

/**
 * A DTO that encapsulates weekly app statistics.
 * 
 * @author Mona.
 */
data class AppStatisticsModel(val weekNum: Int, val year: Int, val requests: Int, val clicks: Int, val installs: Int)

data class AppStatisticsListResponse(val stats: List<AppStatisticsModel>)