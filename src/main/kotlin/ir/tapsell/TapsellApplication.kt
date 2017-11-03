package ir.tapsell

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TapsellApplication

fun main(args: Array<String>) {
    SpringApplication.run(TapsellApplication::class.java, *args)
}
