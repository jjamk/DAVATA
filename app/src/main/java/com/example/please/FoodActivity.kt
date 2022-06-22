package com.example.please

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Filterable
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FoodActivity : AppCompatActivity() {
    val TAG = "FoodActivity"

    lateinit var rv_food_table: RecyclerView
    lateinit var foodlistadapter: FoodListAdapter
    lateinit var foods: ArrayList<FoodList>

    lateinit var f_searchview: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        rv_food_table = findViewById(R.id.f_listView)
        f_searchview = findViewById(R.id.f_searchView)

        f_searchview.setOnQueryTextListener(searchViewTextListener)

        foods = tempFoods()
        setAdapter()
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

    val database = Firebase.database
    val foodref = database.getReference("FoodK")
    val database1 : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef : DatabaseReference = database1.getReference("message")

    fun tempFoods(): ArrayList<FoodList> {

        var tf = ArrayList<FoodList>()
        for (i : Int in 0..7704){
            myRef.setValue("안녕 반가워!")
            var foodref1 = foodref.child(i.toString())
            foodref1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var foodname = snapshot.child("식품명")
                    var foodkal = snapshot.child("에너지(㎉)")
                    var t1 = foodname.children
                    var t2 = foodkal.children
                    for (ds in foodkal.children) {
                        Log.v("foodd", ds.toString())
                    }
                    tf.add(FoodList(foodname.toString().substring(33),
                        foodkal.toString().substring(36)))


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