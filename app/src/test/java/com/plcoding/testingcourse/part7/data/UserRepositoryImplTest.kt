package com.plcoding.testingcourse.part7.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import com.plcoding.testingcourse.part7.domain.User
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryImplTest {
    private lateinit var userRepositoryImpl: UserRepositoryImpl
    private lateinit var api: UserApiFake

    @BeforeEach
    fun setup() {
        api = UserApiFake()
        userRepositoryImpl = UserRepositoryImpl(api)
    }

    @Test
    fun `Test getting profile`() {
            runBlocking {
            val profileResult = userRepositoryImpl.getProfile("1")
                assertThat(profileResult.isSuccess).isTrue()
                assertThat(profileResult.getOrThrow().user.id).isEqualTo("1")

                val expectedPosts = api.posts.filter {
                    it.userId == "1"
                }
                assertThat(profileResult.getOrThrow().posts).isEqualTo(expectedPosts)
        }
    }
}