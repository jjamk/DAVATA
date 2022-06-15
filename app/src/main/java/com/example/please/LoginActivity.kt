package com.example.please

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.please.databinding.ActivityLoginBinding
import com.example.please.databinding.ActivityResisterBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private var _binding:ActivityLoginBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        binding.loginButton.setOnClickListener{
            var email=binding.idEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"로그인 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
        binding.resisterButton.setOnClickListener{
            val intent = Intent(this, ResisterActivity::class.java)
            startActivity(intent)
        }
    }
}