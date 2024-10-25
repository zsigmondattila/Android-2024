package com.example.lab_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab_4.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val message = binding.editText.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("message", message)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("SplashActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SplashActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("SplashActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SplashActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SplashActivity", "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("SplashActivity", "onRestart called")
    }
}
