package com.practice.gitapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    @Profile("dev")
    @Bean
    fun gitWebClient(): WebClient {
        return webClientBuilder()
                .baseUrl("https://api.github.com/")
                .build()
    }
}
