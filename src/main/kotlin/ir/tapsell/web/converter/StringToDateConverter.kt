package ir.tapsell.web.converter

import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author Mona.
 */
@Component
class StringToDateConverter : Converter<String, Date> {
    override fun convert(iso8601Date: String): Date = ISO8601DateFormat().parse(iso8601Date)
}