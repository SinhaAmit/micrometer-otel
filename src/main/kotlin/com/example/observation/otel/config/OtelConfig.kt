package com.example.observation.otel.config

import com.example.observation.otel.filter.OtelWebFilter
import io.micrometer.context.ContextRegistry
import io.micrometer.observation.ObservationRegistry
import io.micrometer.tracing.Tracer
import io.micrometer.tracing.contextpropagation.ObservationAwareBaggageThreadLocalAccessor
import io.micrometer.tracing.contextpropagation.ObservationAwareSpanThreadLocalAccessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OtelConfig {

    @Bean
    fun globalMDCContextFilter(
        tracer: Tracer,
        registry: ObservationRegistry,
    ): OtelWebFilter {
        ContextRegistry.getInstance()
            .registerThreadLocalAccessor(ObservationAwareBaggageThreadLocalAccessor(registry, tracer))
        ContextRegistry.getInstance().registerThreadLocalAccessor(ObservationAwareSpanThreadLocalAccessor(tracer))
        return OtelWebFilter(tracer)
    }
}