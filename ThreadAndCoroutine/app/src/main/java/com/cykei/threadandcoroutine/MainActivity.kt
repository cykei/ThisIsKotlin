package com.cykei.threadandcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cykei.threadandcoroutine.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 방법 1. Thread 객체 이용해서 스레드 생성
        var thread = WorkerThread()
        thread.start()

        // 방법 2. Runnable 인터페이스로 스레드 생성
        var runnableThread = Thread(WorkerRunnable())
        runnableThread.start()

        // 방법 3. 람다식으로 Runnable 익명객체 구현
        Thread{
            var i = 0
            while( i < 10) {
                i++
                Log.i("LambdaThread", "$i")
            }
        }.start()

        // 방법 4. 코틀린에서 제공하는 thread() 구현
        thread(start=true){
           var i = 0
           while ( i < 10 ) {
               i++
               Log.i("KotlinThread", "$i")
           }
        }

    }
}