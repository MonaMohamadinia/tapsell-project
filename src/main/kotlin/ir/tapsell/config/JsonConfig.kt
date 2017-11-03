package ir.tapsell.config

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Responsible for containing all Json related configurations†.
 *
 * @author Mona.
 */
@Configuration
class JsonConfig {

    /**
     * Module that adds support for serialization/deserialization of Kotlin classes and data classes.
     * By default, a default constructor must have existed on the Kotlin object for Jackson to
     * deserialize into the object. With this module, single constructor classes can be used automatically,
     * and those with secondary constructors or static factories are also supported.
     *
     * @see <a href="https://github.com/FasterXML/jackson-module-kotlin">Jackson kotlin module</a>
     */
    @Bean
    fun jacksonCustomizer(): Jackson2ObjectMapperBuilderCustomizer = Jackson2ObjectMapperBuilderCustomizer {
        mapper -> mapper.modules(KotlinModule())
    }
}