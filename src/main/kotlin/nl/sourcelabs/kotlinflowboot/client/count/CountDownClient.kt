package nl.sourcelabs.kotlinflowboot.client.count

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow


@SpringBootApplication
class CountDownClient {

    @Bean
    fun run() = CommandLineRunner {
        runBlocking {
            WebClient.create("http://localhost:8080")
                .get()
                .uri("/count-down")
                .retrieve()
                .bodyToFlow<Int>()
                .collect { println(it) }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CountDownClient>(*args) {
        webApplicationType = WebApplicationType.NONE
    }
}
