package com.practice.gitapi.config

import okhttp3.mockwebserver.MockWebServer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@TestConfiguration
class TestConfig {

    @Bean
    fun mockWebServer(): MockWebServer {
        return MockWebServer()
    }

    @Bean
    fun webClient(mockWebServer: MockWebServer): WebClient {
        return WebClient.builder().baseUrl(mockWebServer.hostName).build()
    }
}