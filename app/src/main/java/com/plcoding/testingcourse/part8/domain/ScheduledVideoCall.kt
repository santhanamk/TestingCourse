package com.plcoding.testingcourse.part8.domain

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Clock
import java.time.LocalDateTime

data class ScheduledVideoCall(
    val title: String,
    val remoteUserId: String,
    val time: LocalDateTime,
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun isExpired(clock: Clock = Clock.systemDefaultZone()): Boolean {
        return time.isBefore(LocalDateTime.now(clock))
    }
}
