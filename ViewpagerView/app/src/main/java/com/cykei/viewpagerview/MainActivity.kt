package com.cykei.viewpagerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cykei.viewpagerview.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //1. 데이터 만들어서
        val textList = listOf("뷰A","뷰B","뷰C","뷰D")
        //2. 어댑터 데이터부분에 데이터 넣고,
        val customAdapter = CustomPagerAdapter()
        customAdapter.textList = textList
        //3. viewPager xml 객체의 어댑터 속성에 만들어놓은 어댑터 연결해주고,
        binding.viewPager.adapter = customAdapter

        //4. 탭에 들어갈 텍스트 생성해서
        val tabTitles = listOf("View A","View B","View C","View D")
        //5. tabLayoutMediator로 탭에 값 넣어주고 해당 탭에 viewPager를 연결해준다.
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }
}