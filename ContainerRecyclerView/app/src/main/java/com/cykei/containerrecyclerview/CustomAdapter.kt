package com.cykei.containerrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cykei.containerrecyclerview.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class CustomAdapter: RecyclerView.Adapter<Holder>() {
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
}

class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
    // 이 어댑터에서 사용할 레이아웃의 이름이 item_recycler 이기 때문에 안드로이드에서 생성해주는 바인딩의 이름은 ItemRecyclerBinding이 된다.
    // item_recycler.xml -> Holder
    fun setMemo(memo: Memo){
        binding.textNo.text = "${memo.no}"
        binding.textTitle.text = "${memo.title}"
        binding.textDate.text = SimpleDateFormat("yyyy/MM/dd").format(memo.timestamp)

    }
}