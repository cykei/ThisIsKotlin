package com.cykei.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
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
