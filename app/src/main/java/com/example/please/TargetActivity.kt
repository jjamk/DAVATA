package com.example.please

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.please.databinding.ActivityTargetBinding

class TargetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTargetBinding
    private val todos = arrayListOf<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTargetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()

        //추가버튼
        binding.addBtn.setOnClickListener{
            addTask()
        }
    }

    private fun initializeViews() {
        //item의 배치방법
        binding.todoList.layoutManager = LinearLayoutManager(this)
        binding.todoList.adapter = TodoAdapter(todos,
            onClickDeleteIcon = {
                deleteTask(it)
            }
        )
    }
    fun addTask() {
        val todo = Todo(binding.addEt.text.toString(),false)
        todos.add(todo)

        //UI 업데이트
        binding.todoList.adapter?.notifyDataSetChanged()
    }
    fun deleteTask(todo:Todo){
        todos.remove(todo)
        binding.todoList.adapter?.notifyDataSetChanged()
    }
}
