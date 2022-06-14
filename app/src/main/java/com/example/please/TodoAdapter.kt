package com.example.please

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.please.TodoAdapter.*
import com.example.please.databinding.ItemTargetBinding
import org.w3c.dom.Text

data class Todo (
    val title: String,
    var completed: Boolean=false,
)
class TodoAdapter(private val todos:List<Todo>,
                  val onClickDeleteIcon: (todo:Todo)->Unit):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    //호출되는 갯수가 정해져있다. parent=recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTargetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return TodoViewHolder(binding).also{ holder ->
            binding.checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
                todos.getOrNull(holder.adapterPosition)?.completed=isChecked
            }
        }

    }

    //불러들일때마다 호출
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
        holder.binding.deleteBtn.setOnClickListener{
            onClickDeleteIcon.invoke(todos[position])
        }
    }

    //Item 몇 개를 가져다줘야하는지
    override fun getItemCount(): Int = todos.size

    class TodoViewHolder(val binding: ItemTargetBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(todo:Todo) {
                binding.todoTitleText.text = todo.title
                binding.checkBox.isChecked = todo.completed
            }
    }
}