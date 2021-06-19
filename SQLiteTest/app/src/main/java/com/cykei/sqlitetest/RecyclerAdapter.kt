package com.cykei.sqlitetest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cykei.sqlitetest.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: SqliteHelper? = null
    var listData = mutableListOf<Memo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root) {
        var m_Memo: Memo? = null // 뷰홀더가 현재 메모 데이터가 뭐가 들어와있는지 알게한다. delete 버튼 누를시 어떤 데이터를 삭제해야하는 알게 하기위함.
        init{
            binding.buttonDelete.setOnClickListener {
                Log.d("delete button", "button is clicked")
                helper?.deleteMemo(m_Memo!!) //deleteMemo()는 null을 허용하지 않는데, m_Memo는 null을 허용하도록 설정되었기때문에 !!를 사용해서 강제해야한다.
                Log.d("delete button", "helper delete item")
                listData.remove(m_Memo)
                Log.d("delete button", "list delete item")
                notifyDataSetChanged()
                Log.d("delete button", "after notify that issue to view")

            }
        }
        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.m_Memo = memo
        }
    }
}

