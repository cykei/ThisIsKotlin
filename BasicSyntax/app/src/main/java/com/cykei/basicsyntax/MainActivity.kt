package com.cykei.basicsyntax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import com.cykei.basicsyntax.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.d("BasicSyntax", "로그를 출력합니다. method = Log.d")

        binding.editTextTextPersonName. addTextChangedListener {
            if(it.toString().length >= 0) {
                Log.d("hello","hello")
            }
        }

        val intent = Intent(this, SubActivity::class.java)
        val listener = object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("리스너", "출력되었습니다.")
                startActivity(intent)
            }
        }

        binding.button.setOnClickListener(listener)
    }
}