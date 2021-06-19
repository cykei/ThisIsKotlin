package com.cykei.cameraandgallery

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.cykei.cameraandgallery.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    // 각 요청에 대한 코드 지정
    val PERM_STORAGE = 9
    val PERM_CAMERA = 10
    val REQ_CAMERA = 11

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 1. 공용 저장소 권한이 있는지 확인
        requirePermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_STORAGE)
    }

    fun initViews() {
        // 2. 카메로 요청시 권한을 먼저 체크하고 승인되었으면 카메라를 연다.
        binding.buttonCamera.setOnClickListener {
            requirePermissions(arrayOf(Manifest.permission.CAMERA), PERM_CAMERA)
        }
    }
    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQ_CAMERA)
    }

    override fun permissionGranted(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> initViews()
            PERM_CAMERA -> openCamera()
        }
    }

    override fun permissionDenied(requestCode: Int) {
        when(requestCode) {
            PERM_STORAGE -> {
                Toast.makeText(this, "공용 저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG).show()
                finish()
            }
            PERM_CAMERA -> Toast.makeText(this, "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // openCamera() 로 카메라를 실행시키면 그 반환값이 여기로 넘어오게된다. 이미지는 3번째 파라미터인 data에 들어있다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                REQ_CAMERA -> {
                    // 미리보기 이미지가 가져와진다.그냥 get() 하면 오브젝트로 가져오기 때문에 비트맵으로 이미지를 변환해줘야 한다.
                    val bitmap = data?.extras?.get("data") as Bitmap
                    binding.imagePreview.setImageBitmap(bitmap)
                }
            }
        }
    }
}