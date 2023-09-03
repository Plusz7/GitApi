package com.practice.gitapi.service

import com.practice.gitapi.repository.GitHubRepository
import com.practice.gitapi.utility.TestUtility
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(MockitoExtension::class, SpringExtension::class)
@SpringBootTest
class GetGitHubServiceTest {

    private lateinit var getGitHubService: GetGitHubService

    @MockK
    private lateinit var gitHubRepository: GitHubRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getGitHubService = GetGitHubService(gitHubRepository)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetRepositoryFromUser() {
        // Mock repository response
        every { gitHubRepository.getRepositoryFromUser("name") } returns TestUtility.userRepositoryDtoListTest

        // Mock branch response
        every {
            gitHubRepository.getBranchesFromRepository(
                "name",
                "repositoryName"
            )
        } returns TestUtility.branchListTest

        // Call the service method
        getGitHubService.getRepositoryFromUser("name")

        // Verify the result or perform assertions
        verify(exactly = 1) { gitHubRepository.getRepositoryFromUser("name") }
        verify(exactly = 1) { gitHubRepository.getBranchesFromRepository("name", "repositoryName") }
    }
}
