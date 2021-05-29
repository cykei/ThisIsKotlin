package com.cykei.basicsyntax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cykei.basicsyntax.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {
    val binding by lazy {ActivitySubBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = intent.getIntExtra("from2",0).toString()

        binding.btnClose.setOnClickListener {
            val returnIntent = Intent() // 돌려줄때는 대상을 지정하지 않아도 된다.
            returnIntent.putExtra("returnValue", binding.editMessage.text.toString())
            setResult(RESULT_OK, returnIntent) // setResult() : 해당 함수를 실행하면 호출한 액티비티로 값이 전달된다.
            finish() // 액티비티를 종료한다.
        }

    }
}