package com.practice.gitapi.model.response

data class UserRepositoryResponse(
    val repositoryName: String,
    val ownerLogin: String,
    val branch: BranchResponse,

)
