package com.ozimos.myapplication.alarm

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class MyDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var listener: DialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as DialogListener?
    }

    override fun onDetach() {
        super.onDetach()
        if (listener != null) {
            listener = null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val date = calendar.get(Calendar.DATE)

        return DatePickerDialog(activity as Context, this, year, month, date)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        listener?.onDateSet(tag = tag, year = year, month = month, dayOfMonth = dayOfMonth)
    }

    interface DialogListener {
        fun onDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int)
    }
}

