package com.example.please.ui.food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.please.databinding.FragmentFoodBinding
import com.google.firebase.database.FirebaseDatabase

class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    //val name=binding2.edtName.text.toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val foodViewModel =
            ViewModelProvider(this).get(FoodViewModel::class.java)
        _binding = FragmentFoodBinding.inflate(inflater, container, false)

        /*myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //키랑,체중 가져와서 bmi계산 후 아래 glide코드로 female버전 다르게 해주기
            }
        })*/
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}