package com.practice.gitapi.model.dto

data class BranchDto(
    val name: String,
    val commit: CommitDto,
    val protected: Boolean
)
