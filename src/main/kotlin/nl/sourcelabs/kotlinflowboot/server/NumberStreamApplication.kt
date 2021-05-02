package nl.sourcelabs.kotlinflowboot.server

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class NumberStreamApplication {

    @GetMapping("/number-stream", produces = [MediaType.APPLICATION_NDJSON_VALUE])
    suspend fun numberFlow() = flow {
        while (true) {
            delay((100..1000).random().toLong())
            emit((0..100).random())
        }
    }
}

fun main(args: Array<String>) {
    runApplication<NumberStreamApplication>(*args)
}
