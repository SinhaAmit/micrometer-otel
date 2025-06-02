package com.example.observation.otel.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class OtelRestController {

    private val logger = LoggerFactory.getLogger(OtelRestController::class.java)

    private val webClient = WebClient.create("http://localhost:8081")

    @GetMapping("/otel/person")
    fun getOtelInfo(): Mono<Person> {
        return Mono.just(Person(1, "John.Doe"))
            .doOnSubscribe { logger.debug("Subscribing for person") }
            .doOnNext { logger.info("Found person $it") }
    }

    @GetMapping("/otel/realm")
    fun getRealmInfo(): Mono<String> {
        return webClient.get()
            .uri("/api/json/realm")
            .retrieve()
            .bodyToMono(String::class.java)
    }
}

data class Person(val id: Int, val name: String)