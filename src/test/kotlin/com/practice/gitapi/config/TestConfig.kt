package com.practice.gitapi.config

import okhttp3.mockwebserver.MockWebServer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.client.WebClient

@TestConfiguration
class TestConfig {

    @Bean
    fun mockWebServer(): MockWebServer {
        return MockWebServer()
    }

    @Profile("test")
    @Bean(name = ["testWebClient"])
    fun webClientMock(mockWebServer: MockWebServer): WebClient {
        return WebClient.builder().baseUrl("http://${mockWebServer.hostName}:${mockWebServer.port}").build()
    }
}
