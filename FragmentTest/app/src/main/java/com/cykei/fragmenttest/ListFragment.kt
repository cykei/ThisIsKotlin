package com.cykei.fragmenttest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cykei.fragmenttest.databinding.FragmentListBinding


class ListFragment : Fragment() {

    lateinit var binding: FragmentListBinding
    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) { //Context : 나를 삽입한 액티비티의 컨텍스트가 들어있다. 여기서는 MainActivity
        super.onAttach(context)
        if(context is MainActivity) mainActivity = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.textTitle.text = arguments?.getString("key1")
        binding.textValue.text = arguments?.getInt("key2").toString()
        //return inflater.inflate(R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            mainActivity.goDetail()
        }
    }
    fun setValue(value:String){
        binding.textFromActivity.text = value
    }
}