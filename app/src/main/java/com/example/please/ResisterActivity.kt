package com.example.please

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.please.databinding.ActivityResisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class ResisterActivity : AppCompatActivity() {
    private var _binding:ActivityResisterBinding?=null
    private val binding get() = _binding!!

    private lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener{
            val id = binding.edtId.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            createUser(id,password)


        }
    }

    private fun createUser(id:String, password:String) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()

        auth.createUserWithEmailAndPassword(id,password).addOnCompleteListener{
            task->
            if (task.isSuccessful) {
                Toast.makeText(this, "환영합니다",
                Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                val name=binding.edtName.text.toString()
                val sex=binding.edtSex.text.toString()
                val weight=binding.edtWeight.text.toString()
                val height=binding.edtHeight.text.toString()
                val age=binding.edtAge.text.toString()
                val myRef: DatabaseReference=database.getReference(name)
                myRef.child("성별").setValue(sex)
                myRef.child("키").setValue(height)
                myRef.child("체중").setValue(weight)
                myRef.child("나이").setValue(age)

                bmi(weight,height)
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
            }
            else{
                Toast.makeText(this, "회원가입 실패",
            Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener{
                Toast.makeText(this, "회원가입 실패",
                Toast.LENGTH_SHORT).show()
            }
    }

    private fun bmi(weight: String, height:String)
    {
        val intent : Intent = Intent(this, MainActivity::class.java)
        val w=weight.toDouble()
        var h=height.toDouble() * 0.01
        intent.putExtra("weight", w)
        intent.putExtra("height", h)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding =null
    }
}