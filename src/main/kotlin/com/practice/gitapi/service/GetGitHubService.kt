package com.practice.gitapi.service

import com.practice.gitapi.model.response.BranchResponse
import com.practice.gitapi.model.response.UserRepositoryResponse
import com.practice.gitapi.repository.GitHubRepository
import org.springframework.stereotype.Service

@Service
class GetGitHubService(
    private val githubRepository: GitHubRepository
) {

    fun getRepositoryFromUser(username: String): List<UserRepositoryResponse> {
        val listOfReposByUser = githubRepository.getRepositoryFromUser(username)
        return if (listOfReposByUser!!.isNotEmpty()) {
            listOfReposByUser.map {
                val repositoryName = it.name
                val ownerLogin = it.owner.login
                val listOfCommitsPerBranch = githubRepository.getBranchesFromRepository(username, repositoryName)
                if (listOfCommitsPerBranch.isNotEmpty()) {
                    val commitName = listOfCommitsPerBranch.first().name
                    val commitSha = listOfCommitsPerBranch.first().commit.sha
                    val branch = BranchResponse(commitName, commitSha)
                    UserRepositoryResponse(repositoryName, ownerLogin, branch)
                } else {
                    return emptyList()
                }
            }
        } else {
            return emptyList()
        }
    }
}
