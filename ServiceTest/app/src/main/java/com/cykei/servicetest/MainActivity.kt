package com.cykei.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
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