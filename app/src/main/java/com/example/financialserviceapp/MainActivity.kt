package com.example.financialserviceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.financialserviceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{
            setContentView(root)
            view=this@MainActivity //데이터바인딩을 위해 연결
        }


    }

    fun openShuffle(){
        startActivity(Intent(this,PinActivity::class.java))

    }

    fun openVerifyOtp(){

    }
}