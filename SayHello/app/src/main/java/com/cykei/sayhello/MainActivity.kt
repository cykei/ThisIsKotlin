package com.cykei.sayhello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cykei.sayhello.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //val binding = ActivityMainBinding.inflate(layoutInflater)
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    // lazy : binding 변수에 지연초기화를 사용해서 특정값을 담을거야.
    // MainActivity클래스는 항상 layoutInflater를 들고이 있다?
    // ActivityMainBinding 을 통해 viewBinding된 모든 xml객체를 가져올 수 있다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)  //viewBinding을 쓰기때문에 직접 activity_main 을 지정해줄 필요가 없다.

//        btnSay.setOnClickListener {
//            //textSay.setText("Hello Kotlin!")  //옛날 자바 방식
//            textSay.text="Hello Kotlin!" // 현재 코틀린 프로퍼티 방식
//        }

        binding.btnSay.setOnClickListener {
            binding.textSay.text = "Hello Kotlin!"
        }
    }
}