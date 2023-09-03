package com.practice.gitapi.repository

import com.practice.gitapi.model.dto.BranchDto
import com.practice.gitapi.model.dto.UserRepositoryDto
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient

@Repository
class GitHubRepository(
    private val webClient: WebClient
) {

    fun getRepositoryFromUser(username: String): List<UserRepositoryDto>? {
        return try {
            webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(UserRepositoryDto::class.java)
                .doOnError { error -> println("Error in WebClient call: ${error.message}") }
                .collectList()
                .doOnSuccess { repositories ->
                    println("Received ${repositories.size} repositories")
                }
                .block()
        } catch (ex: Exception) {
            println("Exception during WebClient call: ${ex.message}")
            emptyList()
        }
    }

    fun getBranchesFromRepository(username: String, repoName: String): List<BranchDto> {
        return try {
            webClient.get()
                .uri("repos/{owner}/{repo}/branches", username, repoName)
                .retrieve()
                .bodyToFlux(BranchDto::class.java)
                .doOnError { error -> println("Error in WebClient call: ${error.message}") }
                .collectList()
                .doOnSuccess { branches ->
                    println("Received ${branches.size} branches")
                }
                .block() ?: emptyList()
        } catch (ex: Exception) {
            println("Exception during WebClient call: ${ex.message}")
            emptyList()
        }
    }
}
