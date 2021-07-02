package com.cykei.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var myService:MyService? = null
    var isService = false
    val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            isService = true
            Log.d("BoundService", "연결되었습니다.")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }
    }

    fun serviceBind(view: View) {
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun serviceUnbind(view: View) {
        if(isService) {
            unbindService(connection)
            isService = false
        }
    }

    fun callServiceFunction(view: View) {
        if( isService ) {
            val message = myService?.serviceMessage()
            Toast.makeText(this, "message=${message}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "서비스가 연결되지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 새로운 메서드를 만들 때 파라미터로 (view: View)를 사용하면 클릭리스너 연결없이도 레이아웃 파일에서 메서드에 직접 접근 가능
    fun serviceStart(view: View){
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_START
        startService(intent)
    }

    fun serviceStop(view: View){
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }
}