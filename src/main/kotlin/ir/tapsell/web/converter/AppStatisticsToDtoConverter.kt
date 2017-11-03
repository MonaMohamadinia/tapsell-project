package ir.tapsell.web.converter

import com.ibm.icu.util.Calendar
import ir.tapsell.model.AppStatistics
import ir.tapsell.web.dto.AppStatisticsModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

/**
 * Responsible to convert an instance of [AppStatistics] to instance of [AppStatisticsModel].
 *
 * @author Mona.
 */
@Component
class AppStatisticsToDtoConverter : Converter<AppStatistics, AppStatisticsModel> {

    /**
     * Covert the given [app] to corresponding [AppStatisticsModel].
     */
    override fun convert(app: AppStatistics): AppStatisticsModel {
        val persianCalendar = "fa_IR@calendar=persian"
        val calendar = Calendar.getInstance(Locale(persianCalendar))
        calendar.time = app.reportTime

        return AppStatisticsModel(
                calendar.get(Calendar.WEEK_OF_YEAR),
                calendar.get(Calendar.YEAR),
                app.videoRequests + app.webViewRequests,
                app.videoClicks + app.webViewClicks,
                app.videoInstalls + app.webViewInstalls
        )
    }
}