package com.cykei.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    inner class MyBinder: Binder() {
        // 액티비티와 서비스가 연결되면 바인더의 getService() 메서드를 통해 서비스에 접근 가능
        fun getService(): MyService {
            return this@MyService
        }
    }

    val binder = MyBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun serviceMessage(): String {
        return " Hello Activity! I am Service!"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("StartedService", "action=$action")
        return super.onStartCommand(intent, flags, startId)
    }

    // 테스트 명령어 모음 - 액티비티에서 서비스를 호출할때 사용한다.
    companion object {
        val ACTION_START = "com.cykei.servicetest.START"
        val ACTION_RUN = "com.cykei.servicetest.RUN"
        val ACTION_STOP = "com.cykei.servicetest.STOP"
    }

    override fun onDestroy() {
        Log.d("SERVICE", "서비스가 종료되었습니다.")
        super.onDestroy()
    }
}
