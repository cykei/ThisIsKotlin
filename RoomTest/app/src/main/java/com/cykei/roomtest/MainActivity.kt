package com.cykei.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.cykei.roomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    var helper: RoomHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "RoomMemo")
            .allowMainThreadQueries()
            .build()

        val adapter = RecyclerAdapter()
        adapter.helper = helper
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf()) // listOf() : helper랑 roomMemoDao() 가 null일때 올 default 값.

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()) {
                val memo = RoomMemo(binding.editMemo.text.toString(), System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)

                // 어댑터의 데이터를 모두 초기화한다. (새로운 데이터를 입력했으니까 그 데이터까지 뷰에 보여주기위해서는 초기화를 해줘야함)
                adapter.listData.clear()
                adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?:listOf())
                adapter.notifyDataSetChanged()

                // 마지막으로 EditText의 메모 내용을 지운다.
                binding.editMemo.setText("")
            }
        }
    }
}