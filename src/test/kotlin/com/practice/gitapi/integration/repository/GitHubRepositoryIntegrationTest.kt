package com.practice.gitapi.integration.repository

import com.practice.gitapi.config.TestConfig
import com.practice.gitapi.repository.GitHubRepository
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TestConfig::class])
class GitHubRepositoryIntegrationTest {

    @Autowired
    private lateinit var gitHubRepository: GitHubRepository

    @Autowired
    private lateinit var mockWebServer: MockWebServer

    @Autowired
    @Qualifier("testWebClient")
    private lateinit var webClientMock: WebClient

    private final val GET_BODY_REPONSE = """
        [
            {
                "id": 1,
                "node_id": "node1",
                "name": "Repo1",
                "full_name": "User1/Repo1",
                "owner": {
                    "login": "User1",
                    "url": "https://github.com/User1"
                },
                "private": false,
                "html_url": "https://github.com/User1/Repo1"
            },
            {
                "id": 2,
                "node_id": "node2",
                "name": "Repo2",
                "full_name": "User1/Repo2",
                "owner": {
                    "login": "User1",
                    "url": "https://github.com/User1"
                },
                "private": true,
                "html_url": "https://github.com/User1/Repo2"
            }
        ]
    """

    @BeforeEach
    fun setup() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(GET_BODY_REPONSE)
        )
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchUserRepos_ShouldReturnCorrectRepos() {
        val repos = gitHubRepository.getRepositoryFromUser("User1")

        assertThat(repos).isNotNull()
        assertThat(repos).hasSize(2)
    }

    @Test
    fun fetchUserRepose_NoUserFoundShouldReturnEmptyList() {
        val repos = gitHubRepository.getRepositoryFromUser("User3")

        assertThat(repos).isNotNull()
        assertThat(repos).isEmpty()
    }
}
