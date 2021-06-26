package com.cykei.cameraandgallery

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.cykei.cameraandgallery.databinding.ActivityMainBinding
import java.lang.Exception
import java.text.SimpleDateFormat

class MainActivity : BaseActivity() {

    // 각 요청에 대한 코드 지정
    val PERM_STORAGE = 9
    val PERM_CAMERA = 10
    val REQ_CAMERA = 11
    val REQ_STORAGE = 12

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
        binding.buttonGallery.setOnClickListener {
            openGallery()
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_STORAGE)
    }

    // 사진을 찍었을 때, 내 폰에 사진이 저장될 위치
    var realUri: Uri? = null

    // 3. 카메라에 찍은 사진을 저장하기 위한 URI를 넘겨준다.
    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        createImageUri(newFileName(),"image/jpg")?.let{ uri ->
            // createImageUri() 가 null이 아니면 작동한다.
            realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
            startActivityForResult(intent, REQ_CAMERA)
        }
        startActivityForResult(intent, REQ_CAMERA)
    }

    // 원본 이미지를 저장할 Uri를 MediaStore(데이터베이스)에 생성하는 메서드
    // Uri (내가 이미지를 저장할 수 있는 위치)를 생성해서 반환해준다.
    fun createImageUri(filename: String, mimeType: String): Uri? {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)

        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

    fun newFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    fun loadBitmap(photoUri: Uri): Bitmap? {
        //var image: Bitmap? = null
        try {
            return if(Build.VERSION.SDK_INT > 27) {
                val source = ImageDecoder.createSource(contentResolver, photoUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, photoUri)
            }
        }catch(e: Exception){
            e.printStackTrace()
        }
        return null
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

    // 4. 카메라를 찍은 후에 호출된다.
    // openCamera() 로 카메라를 실행시키면 그 반환값이 여기로 넘어오게된다. 이미지는 3번째 파라미터인 data에 들어있다.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                REQ_CAMERA -> {
                    // 미리보기 이미지가 가져와진다.그냥 get() 하면 오브젝트로 가져오기 때문에 비트맵으로 이미지를 변환해줘야 한다.
//                    val bitmap = data?.extras?.get("data") as Bitmap
//                    binding.imagePreview.setImageBitmap(bitmap)

                    realUri?.let{ uri ->
                        val bitmap = loadBitmap(uri)
                        binding.imagePreview.setImageBitmap(bitmap)

                        realUri = null
                    }
                }
                REQ_STORAGE -> {
                    data?.data?.let { uri ->
                        binding.imagePreview.setImageURI(uri)
                    }
                }
            }
        }
    }
}