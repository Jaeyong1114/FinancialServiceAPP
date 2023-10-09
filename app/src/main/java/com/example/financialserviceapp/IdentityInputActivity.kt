package com.example.financialserviceapp

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.financialserviceapp.databinding.ActivityIdentityInputBinding
import com.example.financialserviceapp.util.ViewUtil.hideKeyboard
import com.example.financialserviceapp.util.ViewUtil.setOnEditorActionListener
import com.example.financialserviceapp.util.ViewUtil.showKeyboard
import com.example.financialserviceapp.util.ViewUtil.showKeyboardDelay

class IdentityInputActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIdentityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEdit.showKeyboardDelay()

    }

    private fun initView(){
        with(binding){
            nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                if(validName()){
                    nameLayout.error = null
                    if(phoneLayout.isVisible){
                        confirmButton.isVisible = true
                    } else{
                        birthdayLayout.isVisible = true
                        birthdayEdit.showKeyboard()
                        
                    }
                } else {
                    confirmButton.isVisible = false
                    nameLayout.error= "1자 이상의 한글을 입력해 주세요"
                }
                
            }
            birthdayEdit.doAfterTextChanged {
                if( birthdayEdit.length() > 7){
                    if(validBirthday()){
                        birthdayLayout.error = null
                        if(phoneLayout.isVisible){
                            confirmButton.isVisible = true
                        }else {
                            genderlayout.isVisible = true
                            birthdayEdit.hideKeyboard()

                        }
                    } else {
                        confirmButton.isVisible = false
                        birthdayLayout.error = "생년월일 형식이 다릅니다."
                    }

                }
            }

            birthdayEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                val isValid = validBirthday() && birthdayEdit.length() >7
                if(isValid){
                    confirmButton.isVisible = phoneLayout.isVisible
                    birthdayLayout.error = null
                } else {
                    birthdayLayout.error = "생년월일 형식이 다릅니다"
                }
                birthdayEdit.hideKeyboard()
            }

            genderChipGroup.setOnCheckedStateChangeListener{group, checkedIds ->
                if(!telecomlayout.isVisible){
                    telecomlayout.isVisible = true
                }
            }

            telecomChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                if(!phoneLayout.isVisible){
                    phoneLayout.isVisible = true
                    phoneEdit.showKeyboard()
                }

            }
            phoneEdit.doAfterTextChanged {
                if(phoneEdit.length()>10){
                    if(validPhone()) {
                        phoneLayout.error = null
                        confirmButton.isVisible = true
                        phoneEdit.hideKeyboard()
                    } else {
                        confirmButton.isVisible = false
                        phoneLayout.error = "전화번호가 올바르지 않습니다"

                }
            }

            phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){

                    confirmButton.isVisible = phoneEdit.length()>9 && validPhone()
                    phoneEdit.hideKeyboard()
                }
            }

        }




    }
    fun onClickDone(){
        if(!validName()){
            binding.nameLayout.error="1자 이상의 한글을 입력해 주세요"
            return
        }
        if(!validBirthday()){
            binding.birthdayLayout.error="생년월일 형식이 맞지 않습니다"
        }
        if(!validPhone()){
            binding.phoneLayout.error="전화번호가 올바르지 않습니다"
        }
    }

    private fun validName() = !binding.nameEdit.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)
    private fun validBirthday() = !binding.birthdayEdit.text.isNullOrBlank()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEdit.text!!)
    private fun validPhone() = !binding.phoneEdit.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneEdit.text!!)
    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$" //한글만 허용, 2자 이상만 허용
        private const val REGEX_BIRTHDAY = "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])"
        private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})([0-9]{4})"
    }

}