package ir.tapsell.repository

import ir.tapsell.model.AppStatistics
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

/**
 * Provides data access operations for [AppStatistics] document.
 *
 * @author Mona.
 */
interface AppStatisticsRepository : MongoRepository<AppStatistics, String> {

    /**
     * Filters [AppStatistics] by [type] in the given time span starting from [startDate] (inclusive)
     * until [endDate] (inclusive). [AppStatistics] results sorted by the given sort parameter.
     *
     * **Note:** Method results will be cached for twenty minuets based on fist three parameters.
     * Cache key format follows below format:
     * ```
     * type:startDate in millis:endDate in millis
     * ```
     * @see [ir.tapsell.config.CacheConfig]
     */
    @Query("{\$and: [{type: ?0}, {reportTime: {\$gte: ?1, \$lte: ?2}}]}")
    @Cacheable("appStatistics", key = "#p0.toString()+':'+#p1.getTime()+':'+#p2.getTime()")
    fun find(type: Int, startDate: Date, endDate: Date, sort: Sort): List<AppStatistics>

}