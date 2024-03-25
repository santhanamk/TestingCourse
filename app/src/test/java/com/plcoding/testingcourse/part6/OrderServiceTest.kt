package com.plcoding.testingcourse.part6

import com.google.firebase.auth.FirebaseAuth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OrderServiceTest {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailClient: EmailClient
    private lateinit var orderService: OrderService

    @BeforeEach
    fun setUp() {
        firebaseAuth = mockk()
        emailClient = mockk()
        orderService = OrderService(firebaseAuth, emailClient)
        every {firebaseAuth.currentUser?.isAnonymous} returns false
        every { emailClient.send(any()) } returns Unit
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun placeOrder() {
        orderService.placeOrder("email", "Product")

        verify {
            emailClient.send(Email(subject = "Order Confirmation",
                content ="Thank you for your order of Product.",
                recipient = "email"))
        }
    }
}