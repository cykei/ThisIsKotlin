package com.cykei.threadandcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cykei.threadandcoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.*
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

        // 코루틴 //////////

        // 1. 글로벌 코루틴
        GlobalScope.launch {
            // 앱의 생명주기와 함께 동작한다. 별도의 생명주기 관리가 필요 없다.
        }

        // 2. 코루틴 스코프
        binding.btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // 여기서 이미지를 불러오는 작업을 한다.
            }
        }

        // 3. launch와 상태관리
        // 3-1. cancel 예시
        val job = CoroutineScope(Dispatchers.Default).launch {
            val job1 = launch {
                for ( i in 0..10) {
                    delay(500)
                    Log.d("코루틴", "결과 = $i")
                }
            }
        }

        binding.btnStop.setOnClickListener {
            job.cancel() // job뿐만 아니라 job1도 함께 멈춘다.
        }

        // 3-2. join 예시
        CoroutineScope(Dispatchers.Default).launch {
            launch {
                for (i in 0..5) {
                    delay(500)
                    Log.d("코루틴", "결과 1 = $i")
                }
            }.join() // 이거 덕분에 위에꺼가 먼저 실행되고, 그 다음에 아래꺼가 실행된다.

            launch {
                for (i in 0..5) {
                    delay(500)
                    Log.d("코루틴", "결과 2 = $i")
                }
            }
        }

        // 4. async와 반환값 처리
        CoroutineScope(Dispatchers.Default).async {
            val deferred1 = async {
                delay(500)
                350
            }
            val deferred2 = async {
                delay(1000)
                200
            }
            Log.d("코루틴", "연산 결과 = ${deferred1.await() + deferred2. await()}")
        }

        // 5. suspend
        suspend fun subRoutine() {
            for (i in 0..10) {
                Log.d("subRoutine", "$i")
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            // (코드 1)
            subRoutine()
            // (코드 2)
        }

        // 6. withContext
        suspend fun readFile(): String{
            return "파일 내용"
        }
        CoroutineScope(Dispatchers.Main).launch {
            // 화면 처리
            val result = withContext(Dispatchers.IO) {
                readFile()
            }
            Log.d("코루틴", "파일 결과 = ${result}")
        }

        

    }
}