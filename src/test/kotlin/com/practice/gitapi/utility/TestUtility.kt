package com.practice.gitapi.utility

import com.practice.gitapi.model.dto.BranchDto
import com.practice.gitapi.model.dto.CommitDto
import com.practice.gitapi.model.dto.OwnerDto
import com.practice.gitapi.model.dto.UserRepositoryDto

class TestUtility {
    companion object {
        val userRepositoryDtoListTest = listOf(
            UserRepositoryDto(
                id = 1,
                node_id = "id",
                name = "repositoryName",
                full_name = "name",
                owner = OwnerDto("name", "url"),
                private = false,
                html_url = "html",
                description = "something"
            )
        )

        val branchListTest = listOf(
            BranchDto(
                "branch1",
                CommitDto("sha1", "url"),
                true
            )
        )

    }
}