package com.example.please

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.please.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var w=intent.getDoubleExtra("weight",0.0)
        var h=intent.getDoubleExtra("height",0.0)

        if (w/(h*h) <= 18.5) {
            Glide.with(this).load(R.raw.female1).into(binding.avatar)
        }
        if (w/(h*h) > 18.5 && w/(h*h) <= 22.9) {
            Glide.with(this).load(R.raw.female2).into(binding.avatar)
        }
        if (w/(h*h) >= 23.0 && w/(h*h) <= 24.9) {
            Glide.with(this).load(R.raw.female3).into(binding.avatar)
        }
        if (w/(h*h) >= 25.0 ) {
            Glide.with(this).load(R.raw.female4).into(binding.avatar)
        }

        binding.imageButton.setOnClickListener{
            val intent = Intent(this, TargetActivity::class.java)
            startActivity(intent)
        }
        binding.btnCalendar.setOnClickListener{
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
        binding.btnFood.setOnClickListener{
            val intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
        }
        binding.btnExercise.setOnClickListener{
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding.btnSetting.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}