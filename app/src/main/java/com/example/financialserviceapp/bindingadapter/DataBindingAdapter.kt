package com.example.financialserviceapp.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.financialserviceapp.R

@BindingAdapter(value = ["code_text","code_index"])
fun ImageView.setPin(codeText: CharSequence?, index:Int){
    if(codeText !=null){
        if(codeText.length > index){
            System.out.println("코드텍스트"+codeText+"인덱스"+index)
            setImageResource(R.drawable.baseline_circle_black_24)
        }else{
            setImageResource(R.drawable.baseline_circle_gray_24)
        }
    }
}