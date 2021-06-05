package com.cykei.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CustomText : AppCompatTextView {
    constructor(context: Context) : super(context){}
    constructor(context: Context, attrsAttributeSet: AttributeSet) : super(context, attrsAttributeSet){
        val attrList = context.obtainStyledAttributes(attrsAttributeSet, R.styleable.CustomText)

        for (i in 0 until attrList.indexCount) {
            //attrs.xml 의 속성개수만큼 for문이 돌아간다.
            when(attrList.getIndex(i)){
                R.styleable.CustomText_delimiter -> {
                    attrList.getString(attrList.getIndex(i))?.let{
                        // 속성값을 안적으면 null이 있을수 있으니까 let으로 해준다.
                        process(it)
                    }
                }
            }
        }
    }
    constructor(context: Context, attrsAttributeSet: AttributeSet, defStyleAttr: Int) : super(context, attrsAttributeSet, defStyleAttr){}
    fun process(delimiter : String){
        if(text.length == 8) {
            val first4 = text.substring(0, 4)
            val mid2 = text.substring(4,6)
            val last2 = text.substring(6)

            text = first4  + delimiter + mid2 + delimiter + last2
        }
    }

}