package ir.tapsell.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

/**
 * Responsible to configure cache.
 *
 * @author Mona.
 */
@Configuration
@EnableCaching
class CacheConfig : CachingConfigurerSupport() {

    /**
     * For saving key and value in redis, We prefer to use [GenericJackson2JsonRedisSerializer] to serialize object
     * to json for values, and use [StringRedisSerializer] to serialize keys as String.
     */
    @Bean
    fun redisTemplate(cf: RedisConnectionFactory, objectMapper: ObjectMapper): RedisTemplate<String, String> {
        val redisTemplate = RedisTemplate<String, String>()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer(objectMapper)
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.connectionFactory = cf

        return redisTemplate
    }

    /**
     * Configure cache manager to cache elements for twenty minutes.
     */
    @Bean
    fun cacheManager(redisTemplate: RedisTemplate<*, *>): CacheManager {
        val twentyMinuets = 1200L
        val cacheManager = RedisCacheManager(redisTemplate)
        cacheManager.setUsePrefix(true)
        cacheManager.setDefaultExpiration(twentyMinuets)

        return cacheManager
    }
}