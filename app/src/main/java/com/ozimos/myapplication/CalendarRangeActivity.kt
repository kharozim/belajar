package com.ozimos.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.ozimos.myapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarRangeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.myButton)

        button.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val calendar = Calendar.getInstance()
        val today = MaterialDatePicker.todayInUtcMilliseconds()

        calendar.timeInMillis = today
        calendar[Calendar.MONTH] = Calendar.JANUARY
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val janThisYear = calendar.timeInMillis

        calendar.timeInMillis = today
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        val now = calendar.timeInMillis
        Log.e("TAG", "openDatePicker: ${now}, $janThisYear")

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setOpenAt(now)
                .setStart(janThisYear)
                .setEnd(now)
                .setValidator(DateValidatorPointBackward.now())

        val picker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Pilih Kalender")
            .setSelection(
                Pair(
                    MaterialDatePicker.thisMonthInUtcMilliseconds(),
                    MaterialDatePicker.todayInUtcMilliseconds()
                )
            )
            .setCalendarConstraints(constraintsBuilder.build())
            .build()

        picker.show(supportFragmentManager, null)
        picker.addOnPositiveButtonClickListener(object :
            MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> {
            override fun onPositiveButtonClick(selection: Pair<Long, Long>?) {

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selection?.first ?: 0
                val start = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

                calendar.timeInMillis = selection?.second ?: 0
                val end = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

                binding.tvStart.text = start
                binding.tvEnd.text = end
            }
        })
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show()
    }
}