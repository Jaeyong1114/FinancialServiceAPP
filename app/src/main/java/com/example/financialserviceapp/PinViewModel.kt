package com.example.financialserviceapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class PinViewModel: ViewModel() {
    val passwordLiveData: LiveData<CharSequence> by lazy {_passwordLiveData}
    private val _passwordLiveData by lazy{MutableLiveData<CharSequence>()}

    val messageLiveData : LiveData<String> by lazy {_messageLiveData }
    private val _messageLiveData by lazy {MutableLiveData<String>()}

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
        if(validPin()){
            _messageLiveData.value= "설정한 비밀번호는 $password 입니다."
            reset()
        }
        password.replace(0,password.length,"") //패스워드 다 삭제
        _passwordLiveData.value = password.toString()
    }

    //무결성 체크 . 비밀번호 6자리 미만일때, 같은 숫자가 3번이상 반복될때, 연속된 숫자가 반복될 때
    private fun reset(){
        password.replace(0,password.length,"") //패스워드 다 삭제
        _passwordLiveData.value = password.toString()

    }
    private fun validPin() : Boolean{
        if(password.length<6){
            _messageLiveData.value = "비밀번호 6자리를 입력해주세요"
            return false
        }
        if(Pattern.compile("(\\w)\\1\\1").matcher(password.toString()).find()){
            _messageLiveData.value = "3자리 이상 같은 숫자는 사용하실 수 없습니다"
            reset()
            return false
        }

        var count = 0
        password.reduce{before,after -> //reduce의 before 에 반환값이 들어감
            if(after - before == 1){
                count++
                if(count >= 2){
                    _messageLiveData.value = "연속 된 3자리 숫자는 사용하실 수 없습니다"
                    reset()
                    return false
                }
            } else {
                count = 0
            }
            after
        }

        return true
    }


}