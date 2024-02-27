package com.plcoding.testingcourse.part4.presentation

import com.plcoding.testingcourse.part4.domain.AnalyticsLogger
import com.plcoding.testingcourse.part4.domain.LogParam
import org.junit.jupiter.api.BeforeEach

class GoodProfileViewModelTest {
    private lateinit var viewModel: GoodProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GoodProfileViewModel(analytics = object : AnalyticsLogger{
            override fun logEvent(key: String, vararg params: LogParam<Any>) {
                TODO("Not yet implemented")
            }

        })
    }
}