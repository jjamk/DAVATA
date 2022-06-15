package com.example.please.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.please.TargetActivity
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.please.R
import com.example.please.databinding.FragmentHomeBinding
import com.example.please.databinding.ActivityResisterBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_resister.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var _binding2: ActivityResisterBinding?=null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //private val binding2 get() = _binding2!!

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //val name=binding2.edtName.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        /*myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //키랑,체중 가져와서 bmi계산 후 아래 glide코드로 female버전 다르게 해주기
            }
        })*/
        Glide.with(this).load(R.raw.female1).into(binding.avatar)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val imageButton: ImageButton = binding.imageButton
        imageButton.setOnClickListener {
            val intent = Intent(context, TargetActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}