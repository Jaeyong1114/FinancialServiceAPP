package com.example.financialserviceapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import com.example.financialserviceapp.databinding.WidgetShuffleNumberKeypadBinding
import java.util.ArrayList
import kotlin.random.Random

class ShuffleNumberKeypad @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    GridLayout(context,attrs,defStyleAttr), View.OnClickListener {
    //공통으로 처리할예정이라 클래스 윗부분에 onclicklistener



private var _binding: WidgetShuffleNumberKeypadBinding? = null
    private val binding get() = _binding!!

    private var listener : KeyPadListener? = null//메서드를 받는 부분

    init {
        _binding =
            WidgetShuffleNumberKeypadBinding.inflate(LayoutInflater.from(context), this, true)
        binding.view = this //데이터 바인딩
        binding.clickListener=this //데이터 바인딩
        shuffle()
    }


    override fun onDetachedFromWindow() { //뷰가 끝날때 바인딩 참조 끊어줘야함
        super.onDetachedFromWindow()
        _binding = null
    }

    fun shuffle() {
        val keyNumberArray = ArrayList<String>()
        for (i in 0..9) {
            keyNumberArray.add(i.toString())
        }
        binding.gridLayout.children.forEach { view ->
            if (view is TextView && view.tag != null) {
                val randIndex = Random.nextInt(keyNumberArray.size)
                view.text = keyNumberArray[randIndex] //랜덤으로 바꾸고 텍스트뷰에 넣음
                keyNumberArray.removeAt(randIndex) // 넣고 지워야 중복 발생 X

            }
        }
    }

    fun setKeyPadListener(keyPadListener: KeyPadListener){
        this.listener = keyPadListener
    }
    fun onClickDelete(){
        listener?.onClickDelete()
    }
    fun onClickDone(){
        listener?.onClickDone()
    }

    interface KeyPadListener{//외부에서 메서드를 호출 할 수 있게 interface 작성
        fun onClickNum(num: String)
        fun onClickDelete()
        fun onClickDone()

    }

    override fun onClick(v: View) {
        if(v is TextView && v.tag != null){
            listener?.onClickNum(v.text.toString())
        }
    }


}
