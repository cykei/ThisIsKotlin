package com.cykei.fragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.cykei.fragmenttest.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {
    lateinit var binding:FragmentReceiverBinding // onCreateView() 안에서만 사용할 수 있는 파라미터가 필요해서 lateinit으로 선언
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("request"){key, bundle ->
            // 값을 보내는 측 프래그먼트에서 "request"라는 키로 값을 보내면 이 리스터가 작동한다.


            // 아래 방법은 중간에 변수를 하나더 생성하기 때문에 낭비가 존재함.
//            bundle.getString("valueKey")?.let{
//                // let : valueKey에 해당하는 값이 있을때만 화면의 textReceiver에 값을 세팅한다.
//                binding.textReceiver.setText(it)
//                //binding.textReceiver.text = it
//            }

            val valueKey = bundle.getString("valueKey")
            if(valueKey!=null){
                binding.textReceiver.setText(valueKey)
            }
        }
    }
}