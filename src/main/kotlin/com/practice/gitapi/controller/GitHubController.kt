package com.practice.gitapi.controller

import com.practice.gitapi.service.GetGitHubService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/github")
class GitHubController(
    private val getGitHubService: GetGitHubService
) {

    @GetMapping("/user/{username}/repos")
    fun getUserRepositories(@PathVariable username: String): ResponseEntity<Any> {
        val repositories = getGitHubService.getRepositoryFromUser(username)
        return if (repositories.isNotEmpty()) {
            ResponseEntity.ok(repositories)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    mapOf(
                        "status" to HttpStatus.NOT_FOUND.value(),
                        "message" to "User not found."
                    )
                )
        }
    }
}