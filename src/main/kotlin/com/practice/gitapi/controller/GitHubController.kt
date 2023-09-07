package com.practice.gitapi.controller

import com.practice.gitapi.exception.NotAcceptableException
import com.practice.gitapi.model.response.api.ApiResponse
import com.practice.gitapi.service.GetGitHubService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/github")
class GitHubController(
    private val getGitHubService: GetGitHubService
) {
    @GetMapping("/user/{username}/repos")
    fun getUserRepositories(
        @PathVariable username: String,
        @RequestHeader("Accept") acceptHeader: String
    ): ResponseEntity<ApiResponse> {
        if (acceptHeader != "application/json") {
            throw NotAcceptableException()
        }
        val repositories = getGitHubService.getRepositoryFromUser(username)
        return if (repositories.isEmpty()) {
            ResponseEntity(ApiResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = "User not found",
                data = null
            ),
                HttpStatus.NOT_FOUND
            )
        } else {
            ResponseEntity( ApiResponse(
                status = HttpStatus.OK.value(),
                message = "Success",
                data = repositories
            ),
                HttpStatus.OK
            )
        }
    }
}
