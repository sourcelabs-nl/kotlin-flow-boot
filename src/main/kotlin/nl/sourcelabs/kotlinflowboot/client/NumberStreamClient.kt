package nl.sourcelabs.kotlinflowboot.client

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow


@SpringBootApplication
class NumberStreamClient {

    @Bean
    fun run() = CommandLineRunner {
        runBlocking {
            WebClient.create("http://localhost:8080")
                .get()
                .uri("/number-stream")
                .retrieve()
                .bodyToFlow<Int>()
                .collect { println(it) }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<NumberStreamClient>(*args) {
        webApplicationType = WebApplicationType.NONE
    }
}
