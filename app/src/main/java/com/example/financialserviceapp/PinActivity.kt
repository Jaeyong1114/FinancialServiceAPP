package com.example.financialserviceapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.financialserviceapp.databinding.ActivityPinBinding
import com.example.financialserviceapp.widget.ShuffleNumberKeypad

class PinActivity : AppCompatActivity(), ShuffleNumberKeypad.KeyPadListener {

    private lateinit var binding : ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this //라이브 데이터 사용하기위해

        binding.shuffleKeyPad.setKeyPadListener(this)

        viewModel.messageLiveData.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickNum(num: String) {
        viewModel.input(num)
    }

    override fun onClickDelete() {
        viewModel.delete()
    }

    override fun onClickDone() {
        viewModel.done()
    }
}