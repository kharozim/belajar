package com.ozimos.myapplication.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log

class MySMSReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.extras
        try {
            if (bundle != null) {
                /*
                Bundle dengan key "pdus" sudah merupakan standar yang digunakan oleh system
                 */
                val pdusObj = bundle.get("pdus") as Array<*>
                pdusObj.forEach {
                    val currentMessage = getSMS(it as Any, bundle)
                    val sendNumber = currentMessage.displayOriginatingAddress
                    val message = currentMessage.displayMessageBody
                    Log.d("TAG", "senderNum: $sendNumber; message: $message")

                    val showIntent = Intent(context, MySMSReceiver::class.java)
                    showIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    showIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, sendNumber)
                    showIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message)
                    context.startActivity(showIntent)
                }
            }
        } catch (exc: Exception) {

        }
    }

    private fun getSMS(data: Any, bundle: Bundle): SmsMessage {
        val currentSms: SmsMessage
        val format = bundle.getString("format")
        currentSms = if (Build.VERSION.SDK_INT >= 23) {
            SmsMessage.createFromPdu(data as ByteArray, format)
        } else {
            SmsMessage.createFromPdu(data as ByteArray)
        }
        return currentSms
    }
}