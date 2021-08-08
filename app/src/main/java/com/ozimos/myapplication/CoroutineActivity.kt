package com.ozimos.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.ozimos.myapplication.databinding.ActivityCoroutineBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class CoroutineActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGo.setOnClickListener {

//            startProcessExecutor()
            startProcessCoroutine()

        }
    }

    private fun startProcessCoroutine() {
        lifecycleScope.launch(Dispatchers.Default) {
            //simulate process in background thread
            for (i in 1..10) {
                delay(500)
                val percen = i * 10
                withContext(Dispatchers.Main) {
                    //update ui in main thread
                    if (percen == 100) {
                        binding.btnGo.isEnabled = false
                        binding.tvDownload.text = "Download Complete"
                        binding.tvResult.text =
                            "yee kamu berhasil mencjalankan background threat coroutine"
                    } else {
                        binding.btnGo.isEnabled = true
                        binding.tvDownload.text = "Download $percen%"
                        binding.tvResult.text = "background threat corouting sedang dijalankan"
                    }
                }
            }
        }
    }

    private fun startProcessExecutor() {

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            try {
                //simulate process in background thread
                for (i in 1..10) {
                    Thread.sleep(500)
                    val percen = i * 10
                    handler.post {
                        //update ui in main thread
                        if (percen == 100) {
                            binding.tvDownload.text = "Download Complete"
                            binding.tvResult.text =
                                "yee kamu berhasil mencjalankan background treath"
                        } else {
                            binding.tvDownload.text = "Download $percen%"
                            binding.tvResult.text = "background treath sedang dijalankan"
                        }
                    }
                }

            } catch (exc: Exception) {
                exc.printStackTrace()
            }
        }


    }
}