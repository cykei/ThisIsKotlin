package com.cykei.imagedownload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cykei.imagedownload.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonDownload.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                binding.progress.visibility = View.VISIBLE
                val url = binding.editUrl.text.toString()
                val bitmap = withContext(Dispatchers.IO) {
                    loadImage(url)
                    // 로드 이미지가 완료될때까지 다음 줄이 실행되지 않는다.
                }
                binding.imagePreview.setImageBitmap(bitmap)
                binding.progress.visibility = View.GONE
            }
        }
    }
}

suspend fun loadImage(imageUrl: String): Bitmap {
    val url = URL(imageUrl)
    val stream = url.openStream()
    return BitmapFactory.decodeStream(stream)
}