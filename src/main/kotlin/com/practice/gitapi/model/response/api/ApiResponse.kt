package com.practice.gitapi.model.response.api

import com.practice.gitapi.model.response.UserRepositoryResponse

data class ApiResponse(
    val status: Int,
    val message: String,
    val data: List<UserRepositoryResponse>?
)
