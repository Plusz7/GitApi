package com.practice.gitapi.model.dto

import com.practice.gitapi.model.dto.OwnerDto

data class UserRepositoryDto(
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val owner: OwnerDto,
    val private: Boolean,
    val html_url: String,
    val description: String?,
)