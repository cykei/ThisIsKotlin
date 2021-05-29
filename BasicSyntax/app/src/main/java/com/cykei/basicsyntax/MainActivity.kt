package com.cykei.basicsyntax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.cykei.basicsyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("BasicSyntax", "로그를 출력합니다. method = Log.d")

        binding.editTextTextPersonName.addTextChangedListener {
            if (it.toString().length >= 0) {
                Log.d("hello", "hello")
            }
        }

        val intent = Intent(this, SubActivity::class.java)
        intent.putExtra("from1", "Hello Bundle")
        intent.putExtra("from2", 2021)

        val listener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("리스너", "출력되었습니다.")

                // requestCode (두번째 인자) : 여러개의 버튼이 있고, 각 버튼으로 열리는 액티비티가 전부 메세지를 반환하는 경우, 각 액티비티를 구분하기위해 임의로 지정해주는 숫자
                startActivityForResult(intent, 99)
            }
        }

        binding.button.setOnClickListener(listener)

        // -- 스피너 테스트 --
        // 1. 데이터를 작성합니다.
        var data = listOf("- 선택하세요 -", "BASIC", "EXPERT", "MASTER")
        // 2. 어댑터를 생성해서 레이아웃과 데이터를 넣어줍니다. 레이아웃은 구글이 기본으로 제공해주는 simple_list_item_1 레이아웃을 사용합니다.
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        // 3. 스피너뷰의 어댑터 속성에 내가 생성한 어댑터를 연결해줍니다.
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               binding.resultSpinner.text = data.get(position)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                99 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}