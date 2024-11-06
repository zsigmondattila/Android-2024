package com.example.lab_4

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.lab_4.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logo.alpha = 0f
        binding.logo.animate().setDuration(1500).alpha(1f)


        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, 2000)
    }
}
