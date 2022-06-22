package com.example.please

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Filterable
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.please.databinding.ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    val TAG = "FoodActivity"

    private lateinit var binding: ActivityFoodBinding
    lateinit var rv_food_table: RecyclerView
    lateinit var foodlistadapter: FoodListAdapter
    lateinit var foods: ArrayList<FoodList>

    lateinit var f_searchview: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_food_table = findViewById(R.id.f_listView)
        f_searchview = findViewById(R.id.f_searchView)

        f_searchview.setOnQueryTextListener(searchViewTextListener)

        foods = tempFoods()
        setAdapter()

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

    //SearchView 텍스트 입력시 이벤트
    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            //검색버튼 입력시 호출, 검색버튼이 없으므로 사용하지 않음
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            //텍스트 입력/수정시에 호출
            override fun onQueryTextChange(s: String): Boolean {
                foodlistadapter.filter.filter(s)
                Log.d(TAG, "SearchVies Text is changed : $s")
                return false
            }
        }

    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_food_table.layoutManager = LinearLayoutManager(this)
        foodlistadapter = FoodListAdapter(foods, this)
        rv_food_table.adapter = foodlistadapter
    }


    fun tempFoods(): ArrayList<FoodList> {
        var tf = ArrayList<FoodList>()
        tf.add(FoodList("김치찌개", 432.2))
        tf.add( FoodList("된장찌개", 533.2))
        tf.add( FoodList("오므라이스", 611.1))
        tf.add( FoodList("싸이버거", 411.3))
        tf.add( FoodList("식빵", 912.2))
        tf.add( FoodList("초콜렛", 142.3))
        return tf
    }
}