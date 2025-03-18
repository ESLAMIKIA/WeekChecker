package com.example.weekchecker

import android.content.Context
import android.content.SharedPreferences

object WeekPreferences {
    private const val PREFS_NAME = "week_prefs"
    private const val KEY_IS_EVEN_WEEK = "is_even_week"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun isEvenWeek(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_IS_EVEN_WEEK, true) // Default : even
    }

    fun toggleWeek(context: Context) {
        val currentStatus = isEvenWeek(context)
        getPreferences(context).edit().putBoolean(KEY_IS_EVEN_WEEK, !currentStatus).apply()
    }
}
