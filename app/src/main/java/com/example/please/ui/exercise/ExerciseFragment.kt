package com.example.please.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.please.databinding.FragmentExerciseBinding
import com.example.please.ui.food.ExerciseViewModel
import com.google.firebase.database.FirebaseDatabase

class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null

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
            ViewModelProvider(this).get(ExerciseViewModel::class.java)
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}