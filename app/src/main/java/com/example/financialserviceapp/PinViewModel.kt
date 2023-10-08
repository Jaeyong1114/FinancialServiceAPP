package com.example.financialserviceapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PinViewModel: ViewModel() {
    val passwordLiveData: LiveData<CharSequence> by lazy {_passwordLiveData}
    private val _passwordLiveData by lazy{MutableLiveData<CharSequence>()}

    private val password: StringBuffer = StringBuffer("")

    fun input(num: String){
        if(password.length < 6){
            password.append(num)
            _passwordLiveData.value = password.toString()
            Log.e("PinViewModel","패스워드:${password},라이브데이터:${passwordLiveData},_라이브데이터:${_passwordLiveData.value}")
        }
    }

    fun delete(){
        if(password.isNotEmpty()){
            password.deleteCharAt(password.length - 1) // 패스워드에 가장마지막 부분 지움
            _passwordLiveData.value = password.toString()
        }
    }

    fun done(){
        password.replace(0,password.length,"") //패스워드 다 삭제
        _passwordLiveData.value = password.toString()
    }
}