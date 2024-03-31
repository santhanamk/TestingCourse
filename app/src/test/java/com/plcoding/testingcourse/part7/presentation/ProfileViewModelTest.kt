package com.plcoding.testingcourse.part7.presentation

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import com.plcoding.testingcourse.part7.data.UserRepositoryFake
import com.plcoding.testingcourse.util.MainCoroutineExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class ProfileViewModelTest {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var userRepositoryFake: UserRepositoryFake

    @BeforeEach
    fun setup() {
        userRepositoryFake = UserRepositoryFake()
        profileViewModel = ProfileViewModel(
            userRepositoryFake,
            SavedStateHandle(
                initialState =
                mapOf("userId" to userRepositoryFake.profileToReturn.user.id)
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test loading profile success`() {
        runTest {
            profileViewModel.loadProfile()
            advanceUntilIdle()
            assertThat(profileViewModel.state.value.profile).isEqualTo(userRepositoryFake.profileToReturn)
            assertThat(profileViewModel.state.value.isLoading).isEqualTo(false)
        }
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Test loading profile error`() {
            runTest {
                userRepositoryFake.errorToReturn = Exception("Test exception")
                profileViewModel.loadProfile()
                advanceUntilIdle()
                assertThat(profileViewModel.state.value.profile).isNull()
                assertThat(profileViewModel.state.value.errorMessage).isEqualTo("Test exception")
                assertThat(profileViewModel.state.value.isLoading).isFalse()
            }
        }
    }
