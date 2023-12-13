package com.example.assignment16

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment16.databinding.FragmentWelcomeBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun finish() {
        super.finish()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(Intent(this, FragmentWelcomeBinding:: class.java))
    }

}