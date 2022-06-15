package com.example.please

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler(Looper.getMainLooper()).postDelayed({
            // 일정 시간이 지나면 MainActivity로 이동
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // 이전 키를 눌렀을 때 스플래스 스크린 화면으로 이동을 방지하기 위해
            // 이동한 다음 사용안함으로 finish 처리
            finish()
        },2000) //2초 후 실행
    }
}