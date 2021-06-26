package com.cykei.threadandcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cykei.threadandcoroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var thread = WorkerThread()
        thread.start()
    }
}