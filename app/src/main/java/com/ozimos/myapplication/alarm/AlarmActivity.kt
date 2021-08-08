package com.ozimos.myapplication.alarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozimos.myapplication.databinding.ActivityAlarmBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmActivity : AppCompatActivity(), MyDatePicker.DialogListener,
    MyTimePicker.MyTimeListener {

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
        private const val TIME_PICKER_REPEAT_TAG = "TimePickerRepeat"
    }

    private val binding by lazy { ActivityAlarmBinding.inflate(layoutInflater) }
    private lateinit var alarmReceiver: MyAlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        alarmReceiver = MyAlarmReceiver()
        setOnclickBottom()

    }

    private fun setOnclickBottom() {
        binding.run {
            btnOnceDate.setOnClickListener {
                val datePicker = MyDatePicker()
                datePicker.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            btnOnceTime.setOnClickListener {
                val timePicker = MyTimePicker()
                timePicker.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }
            btnSetOnceAlarm.setOnClickListener {
                val onceDate = tvOnceDate.text.toString()
                val onceTime = tvOnceTime.text.toString()
                val onceMessage = edtOnceMessage.text.toString()

                alarmReceiver.setOneTimeAlarm(
                    this@AlarmActivity,
                    MyAlarmReceiver.TYPE_ONE_TIME,
                    onceDate,
                    onceTime,
                    onceMessage
                )

            }

            btnRepeatingTime.setOnClickListener {
                val timePickerRepeate = MyTimePicker()
                timePickerRepeate.show(supportFragmentManager, TIME_PICKER_REPEAT_TAG)
            }
            btnSetRepeatingAlarm.setOnClickListener {
                val repeateAlarm = binding.tvRepeatingTime.text.toString()
                val repeateMessage = binding.edtRepeatingMessage.text.toString()
                alarmReceiver.setRepeatingAlarm(
                    this@AlarmActivity,
                    MyAlarmReceiver.TYPE_REPEATING,
                    repeateAlarm,
                    repeateMessage
                )
            }

            btnCancelRepeatingAlarm.setOnClickListener {
                alarmReceiver.cancelAlarm(this@AlarmActivity, MyAlarmReceiver.TYPE_REPEATING)
            }
        }
    }

    override fun onDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        // Set text dari textview once
        binding.tvOnceDate.text = dateFormat.format(calendar.time)
    }

    override fun onTImeSet(tag: String?, hourOfDay: Int, minute: Int) {
        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when (tag) {
            TIME_PICKER_ONCE_TAG -> binding.tvOnceTime.text = dateFormat.format(calendar.time)
            TIME_PICKER_REPEAT_TAG -> {
                binding.tvRepeatingTime.text = dateFormat.format(calendar.time)
            }
            else -> {
            }
        }
    }
}