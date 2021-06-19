package com.cykei.cameraandgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BaseActivity() {
    override fun permissionGranted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun permissionDenied(requestCode: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}