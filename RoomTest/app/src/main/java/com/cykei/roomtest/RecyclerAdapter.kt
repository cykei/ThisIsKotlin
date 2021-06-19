package com.cykei.roomtest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cykei.roomtest.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: RoomHelper? = null
    var listData = mutableListOf<RoomMemo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val roomMemo = listData.get(position)
        holder.setRoomMemo(roomMemo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root) {
        var m_RoomMemo: RoomMemo? = null // 뷰홀더가 현재 메모 데이터가 뭐가 들어와있는지 알게한다. delete 버튼 누를시 어떤 데이터를 삭제해야하는 알게 하기위함.
        init{
            binding.buttonDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(m_RoomMemo!!) //deleteRoomMemo()는 null을 허용하지 않는데, m_RoomMemo는 null을 허용하도록 설정되었기때문에 !!를 사용해서 강제해야한다.
                listData.remove(m_RoomMemo)
                notifyDataSetChanged()
            }
        }
        fun setRoomMemo(roomMemo: RoomMemo) {
            binding.textNo.text = "${roomMemo.no}"
            binding.textContent.text = roomMemo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(roomMemo.dateTime)}"
            this.m_RoomMemo = roomMemo
        }
    }
}

