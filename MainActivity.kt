package com.example.weekchecker

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weekchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateWeekStatus()

        binding.button.setOnClickListener {
            WeekPreferences.toggleWeek(this)
            updateWeekStatus()
            WeekWidget.updateAllWidgets(this)
            // Update
            updateWidget()
        }
       WorkScheduler.scheduleWeeklyUpdate(this)


    }


    private fun updateWeekStatus() {
        val isEvenWeek = WeekPreferences.isEvenWeek(this)
        if (isEvenWeek){
            binding.textView.text = "هفته زوج"
            binding.main.setBackgroundResource(R.drawable.background1)
            binding.button.setBackgroundColor(Color.RED)
        }
        else{
            binding.textView.text = "هفته فرد"
            binding.main.setBackgroundResource(R.drawable.background2)
            binding.button.setBackgroundColor(Color.BLUE)
        }
//        binding.textView.text = if (isEvenWeek) "هفته زوج" else "هفته فرد"
    }

    private fun updateWidget() {
        val intent = Intent(this, NewAppWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        val ids = AppWidgetManager.getInstance(this)
            .getAppWidgetIds(ComponentName(this, NewAppWidget::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(intent)
    }

}
