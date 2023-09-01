package com.practice.gitapi.repository

import com.practice.gitapi.model.dto.BranchDto
import com.practice.gitapi.model.dto.UserRepositoryDto
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient

@Repository
class GitHubRepository(    private val webClient: WebClient
) {

    fun getRepositoryFromUser(username: String): List<UserRepositoryDto> {

        return webClient.get()
            .uri("/users/{username}/repos", username)
            .retrieve()
            .bodyToFlux(UserRepositoryDto::class.java)
            .collectList()
            .block() ?: emptyList()
    }

    fun getBranchesFromRepository(username: String, repoName: String): List<BranchDto> {
        return webClient.get()
            .uri("repos/{owner}/{repo}/branches", username, repoName)
            .retrieve()
            .bodyToFlux(BranchDto::class.java)
            .collectList()
            .block() ?: emptyList()
    }
}