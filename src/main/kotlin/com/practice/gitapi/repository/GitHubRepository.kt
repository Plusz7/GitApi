package com.practice.gitapi.repository

import com.practice.gitapi.model.dto.BranchDto
import com.practice.gitapi.model.dto.UserRepositoryDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Repository
class GitHubRepository(
    @Autowired
    private val webClientBuilder: WebClient.Builder
) {

    private val webClient: WebClient = webClientBuilder.baseUrl("https://api.github.com/").build()

    fun getRepositoryFromUser(username: String): List<UserRepositoryDto> {
        return webClient.get()
            .uri("/users/{username}/repos", username)
            .retrieve()
            .bodyToFlux(UserRepositoryDto::class.java)
            .onErrorResume { error ->
                println("$error in WebClient call: ${error.message}")
                Mono.empty() // Return an empty Mono on error
            }
            .collectList()
            .doOnSuccess { repositories ->
                println("Received ${repositories.size} repositories")
            }
            .block() ?: emptyList()
    }

    fun getBranchesFromRepository(username: String, repoName: String): List<BranchDto> {
        return webClient.get()
            .uri("repos/{owner}/{repo}/branches", username, repoName)
            .retrieve()
            .bodyToFlux(BranchDto::class.java)
            .onErrorResume { error ->
                println("$error in WebClient call: ${error.message}")
                Mono.empty() // Return an empty Mono on error
            }
            .collectList()
            .doOnSuccess { branches ->
                println("Received ${branches.size} branches")
            }
            .block() ?: emptyList()
    }
}
