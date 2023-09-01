package com.practice.gitapi.controller

import com.practice.gitapi.model.dto.BranchDto
import com.practice.gitapi.model.dto.UserRepositoryDto
import com.practice.gitapi.model.response.UserRepositoryResponse
import com.practice.gitapi.service.GetGitHubService
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
    fun getUserRepositories(@PathVariable username: String): List<UserRepositoryResponse> {
        return getGitHubService.getRepositoryFromUser(username)
    }
}