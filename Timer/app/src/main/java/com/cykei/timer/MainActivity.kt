package com.cykei.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.cykei.timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var total = 0 //전체 시간
        var started = false

        // total에 입력되있는 시간을 화면에 표시해주는 핸들러 구현
        val handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                val minute = String.format("%02d", total/60)
                val second = String.format("%02d", total%60)
                binding.textTimer.text = "$minute:$second"
            }
        }

        // 시작버튼이 눌리면 1초에 한번씩 total의 값을 1씩 증가시키고 핸들러에 메시지를 전송한다.
        binding.buttonStart.setOnClickListener {
            if (started == false && total !=0 ) {
                total = 0
                binding.textTimer.text = "00:00"
            }
            started = true
            thread(start=true){
                while( started ) {
                    Thread.sleep(1000)
                    if (started) {
                        total++
                        handler?.sendEmptyMessage(0) //핸들러를 호출하는 곳이 한곳 밖에 없음으로 그냥 0을 담아 호출한다.
                    }
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            if (started) {
                started = false
            }
        }
    }
}