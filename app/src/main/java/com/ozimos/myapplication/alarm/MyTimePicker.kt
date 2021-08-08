package com.ozimos.myapplication.alarm

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.CalendarView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class MyTimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private var listener: MyTimeListener? = null

    interface MyTimeListener {
        fun onTImeSet(tag: String?, hourOfDay: Int, minute: Int)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(context, this, hourOfDay, minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        listener?.onTImeSet(tag, hourOfDay, minute)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MyTimeListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (listener != null) {
            listener = null
        }
    }

}