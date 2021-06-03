package com.cykei.fragmenttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.cykei.fragmenttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    val detailFragment by lazy {DetailFragment()} //디테일 프래그먼트를 매번만드는게 아니라 한번 만들어놓고 계속 쓰겠다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setFragment()
    }

    fun setFragment() {
        // 1. 사용할 프레그먼트 생성
        val listFragment = ListFragment()
        // 2. 트랜잭션 생성
        val transaction = supportFragmentManager.beginTransaction()
        // 3. 트랜잭션을 통해 프래그먼트 삽입
        transaction.add(R.id.frameLayout, listFragment)
        transaction.commit()
    }

    fun goDetail() {
       // val detailFragment = DetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, detailFragment)
        transaction.addToBackStack("detail") //뒤로가기 기능 구현. (이거 없으면 뒤로가기 눌렀을때 액티비티가 종료된다)
        // 뒤로가기 버튼 객체에 해당 트랜잭션을 추가해서 뒤로가기를 눌렀을때 이 트랜잭션 실행전으로 돌아가게 만든다.
        transaction.commit()
    }

    fun goBack() {
        onBackPressed() // 뒤로가기를 실행한다.
    }
}