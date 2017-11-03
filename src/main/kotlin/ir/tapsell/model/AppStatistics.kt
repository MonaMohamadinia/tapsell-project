package ir.tapsell.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

/**
 * Encapsulates statistics for a given type of application.
 *
 * @author Mona.
 */
@Document(collection = "appStatistics")
data class AppStatistics(
        @Id
        val id: String,
        val reportTime: Date,
        val type: Int,
        val videoRequests: Int,
        val webViewRequests: Int,
        val videoClicks: Int,
        val webViewClicks: Int,
        val videoInstalls: Int,
        val webViewInstalls: Int
)



