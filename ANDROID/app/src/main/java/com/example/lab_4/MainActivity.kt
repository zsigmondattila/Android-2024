package com.example.lab_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.welcomeTextView.text = "Welcome to RecipeHub!"

        binding.getStartedButton.setOnClickListener {

        }
    }
}
