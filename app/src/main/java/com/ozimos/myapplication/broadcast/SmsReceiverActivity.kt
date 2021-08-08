package com.ozimos.myapplication.broadcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ozimos.myapplication.R
import com.ozimos.myapplication.databinding.ActivitySmsReceiverBinding

class SmsReceiverActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"
    }

    private val binding by lazy { ActivitySmsReceiverBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {

            btnClose.setOnClickListener {
                finish()
            }

            val senderNo = intent.getStringExtra(EXTRA_SMS_NO)
            val senderMessege = intent.getStringExtra(EXTRA_SMS_MESSAGE)

            tvFrom.text = "from : $senderNo"
            tvMessage.text = senderMessege
        }


    }
}