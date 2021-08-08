package com.ozimos.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozimos.myapplication.alarm.AlarmActivity
import com.ozimos.myapplication.broadcast.BroadcastActivity
import com.ozimos.myapplication.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            menuCalendarRange.setOnClickListener {
                startActivity(Intent(this@MenuActivity, CalendarRangeActivity::class.java))
            }

            menuCoroutine.setOnClickListener {
                startActivity(Intent(this@MenuActivity, CoroutineActivity::class.java))
            }

            menuBroadcast.setOnClickListener {
                startActivity(Intent(this@MenuActivity, BroadcastActivity::class.java))
            }
            menuAlarm.setOnClickListener {
                startActivity(Intent(this@MenuActivity, AlarmActivity::class.java))
            }
        }

    }
}