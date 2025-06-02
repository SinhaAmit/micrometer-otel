package com.example.observation.otel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicrometerOtelApplication

fun main(args: Array<String>) {
	runApplication<MicrometerOtelApplication>(*args)
}
