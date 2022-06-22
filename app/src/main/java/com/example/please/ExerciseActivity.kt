package com.example.please

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.please.databinding.ActivityCalendarBinding
import com.example.please.databinding.ActivityExerciseBinding
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {
    val TAG = "ExerciseActivity"

    lateinit var rv_exer_table: RecyclerView
    lateinit var exerlistadapter: ExerListAdapter
    lateinit var exers: ArrayList<ExerList>
    private lateinit var binding: ActivityExerciseBinding

    lateinit var e_searchview: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_exer_table = findViewById(R.id.e_listView)
        e_searchview = findViewById(R.id.e_searchView)

        e_searchview.setOnQueryTextListener(searchViewTextListener)

        exers = tempExers()
        setAdapter()
        eb_insert.setOnClickListener{
            textView2.text=" 농구 6.7kcal 60분"
            textView4.text="오늘 운동으로 소모한 칼로리는 402kcal입니다."
        }
        binding.btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
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
                exerlistadapter.filter.filter(s)
                Log.d(TAG, "SearchVies Text is changed : $s")
                return false
            }
        }

    fun setAdapter(){
        //리사이클러뷰에 리사이클러뷰 어댑터 부착
        rv_exer_table.layoutManager = LinearLayoutManager(this)
        exerlistadapter = ExerListAdapter(exers, this)
        rv_exer_table.adapter = exerlistadapter
    }

    val database = Firebase.database
    val exerref = database.getReference("ExerC")
    val database1 : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef : DatabaseReference = database1.getReference("message")

    fun tempExers(): ArrayList<ExerList> {
        myRef.setValue("안녕 반가워!")
        var tf = ArrayList<ExerList>()
        for (i : Int in 0..51){
            myRef.setValue("안녕 반가워!")
            var exerref1 = exerref.child(i.toString())
            exerref1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var exername = snapshot.child("ExerKinds")
                    var exerkal = snapshot.child("Kper1m")
                    var t1 = exername.children
                    var t2 = exerkal.children
                    for (ds in exerkal.children) {
                        Log.v("exerr", ds.toString())
                    }
                    tf.add(ExerList(exername.toString().substring(40),exerkal.toString().substring(37)))


                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("snap", "error1")
                }
            })
            /*
            var foodname:String = foodref.child(i.toString()).child("식품명").key.toString()
            var foodkal:String = foodref.child(i.toString()).child("에너지(㎉)").toString()
            Log.v("msg",foodkal)
            Log.v("msg1",foodname)
            tf.add(FoodList(foodname,foodkal))

             */
        }
        return tf
        /*
        tf.add(FoodList("김치찌개", 432.2))
        tf.add( FoodList("된장찌개", 533.2))
        tf.add( FoodList("오므라이스", 611.1))
        tf.add( FoodList("싸이버거", 411.3))
        tf.add( FoodList("식빵", 912.2))
        tf.add( FoodList("초콜렛", 142.3))

         */

    }
}


