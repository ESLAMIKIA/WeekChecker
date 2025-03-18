package com.example.weekchecker

import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

object WorkScheduler {
    fun scheduleWeeklyUpdate(context: Context) {
        val workRequest = PeriodicWorkRequestBuilder<AutoWeekChangerWorker>(7, TimeUnit.DAYS)
            .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "weekly_week_update",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    private fun calculateInitialDelay(): Long {
        val calendar = Calendar.getInstance()

        // get the time
        val now = System.currentTimeMillis()

        // Change every friday
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
        calendar.set(Calendar.HOUR_OF_DAY, 0) // Friday at 00:00
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        // If today is friday
        if (calendar.timeInMillis <= now) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }

        return calendar.timeInMillis - now
    }
}
