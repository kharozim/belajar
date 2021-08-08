package com.ozimos.myapplication.broadcast

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ozimos.myapplication.databinding.ActivityBroadcastctivityBinding

class BroadcastActivity : AppCompatActivity() {

    companion object {
        const val ACTION_DOWNLOAD_STATUS = "download_status"
        private const val SMS_REQUEST_CODE = 101
    }

    private val binding by lazy { ActivityBroadcastctivityBinding.inflate(layoutInflater) }
    private lateinit var downloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initDownloadReceiver()

        binding.broadcastSmsPermision.setOnClickListener {
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)
        }
        binding.broadcastDownload.setOnClickListener {
            val downloadServiceIntent = Intent(this, DownloadService::class.java)
            startService(downloadServiceIntent)
        }

    }

    private fun initDownloadReceiver() {
        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.e("TAG", "onReceive: download selesai")
                Toast.makeText(this@BroadcastActivity, "download selesai", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val intentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, intentFilter)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST_CODE) {
            when {
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(this, "SMS receiver Permision diterima", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(this, "SMS receiver Permision ditolak", Toast.LENGTH_SHORT)
                        .show()

                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(downloadReceiver)
    }
}