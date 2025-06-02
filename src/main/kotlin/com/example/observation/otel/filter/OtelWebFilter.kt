package com.example.observation.otel.filter

import io.micrometer.tracing.Tracer
import io.micrometer.tracing.contextpropagation.reactor.ReactorBaggage
import org.slf4j.LoggerFactory
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class OtelWebFilter(private val tracer: Tracer) : WebFilter {

    private val logger = LoggerFactory.getLogger(OtelWebFilter::class.java)

    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> {
        val baggage = mapOf(
            "username" to exchange.request.headers.getFirst("X-Username").orEmpty(),
            "tenantId" to exchange.request.headers.getFirst("X-Tenant-Id").orEmpty(),
        )
        //tracer.createBaggageInScope("username", exchange.request.headers.getFirst("X-Username").orEmpty())
        //tracer.createBaggageInScope("tenantId", exchange.request.headers.getFirst("X-Tenant-Id").orEmpty())
        return chain.filter(exchange)
            .doOnSubscribe { logger.debug("Setting baggage for path: {}", exchange.request.path) }
            .doOnSuccess { logger.debug("Setting baggage completed") }
            .contextWrite(ReactorBaggage.append(baggage))
    }
}