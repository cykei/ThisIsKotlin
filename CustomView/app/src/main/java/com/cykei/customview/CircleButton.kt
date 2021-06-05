package com.cykei.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CircleButton : AppCompatButton {
    // 415쪽 미니퀴즈 5-4
    // 커스텀뷰 클래스를 하나 만들고 빨간색 도넛 모양을 STROKE 스타일을 사용하지 않고 만들어보세요.
    constructor(context: Context) : super(context){
        val canvas = Canvas()
        val red = Paint()
        red.style = Paint.Style.FILL
        red.color = Color.RED
        canvas?.drawCircle(150f, 300f,100f, red)

        val white = Paint()
        white.style = Paint.Style.FILL
        white.color = Color.WHITE
        canvas?.drawCircle(150f, 300f, 90f, white)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

//    override fun onDraw(canvas: Canvas?) {
//       // super.onDraw(canvas)
//
//        val red = Paint()
//        red.style = Paint.Style.FILL
//        red.color = Color.RED
//        canvas?.drawCircle(150f, 300f,100f, red)
//
//        val white = Paint()
//        white.style = Paint.Style.FILL
//        white.color = Color.WHITE
//        canvas?.drawCircle(150f, 300f, 90f, white)
//    }
}