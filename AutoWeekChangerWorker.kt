package com.example.weekchecker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AutoWeekChangerWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // change week in SharedPreferences
        WeekPreferences.toggleWeek(applicationContext)

        // Updating Widget
        WeekWidget.updateAllWidgets(applicationContext)


        return Result.success()
    }
}
