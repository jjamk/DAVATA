package com.example.please

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodListAdapter(var Food: ArrayList<FoodList>, var con: Context) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>(), Filterable {
    var TAG = "FoodListAdapter"

    var filteredFoods = ArrayList<FoodList>()
    var itemFilter = ItemFilter()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var f_name: TextView
        var choice: Button

        init {
            f_name = itemView.findViewById(R.id.f_name)
            choice = itemView.findViewById(R.id.choice)
            choice.setOnClickListener {
                //intent사용하여 text값 넘기고 칼로리 합쳐서 표시할 것
            }
        }
    }

    init {
        filteredFoods.addAll(Food)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_foodrv, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food: FoodList = filteredFoods[position]
        //[수정요함] 이미지 작업의 경우 glide를 사용해 server의 image를 불러올 것
        //holder.iv_person_phone_book_list_item
        holder.f_name.text = food.foodname
    }

    override fun getItemCount(): Int {
        return filteredFoods.size
    }

    //-- filter
    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.v(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<FoodList> = ArrayList<FoodList>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = Food
                results.count = Food.size

                return results
                //빈칸이 아닐경우 filter검색
            } else if (filterString.trim { it <= ' ' }.isNotEmpty()) {
                for (fd in Food) {
                    if (fd.foodname.contains(filterString)) {
                        filteredList.add(fd)
                    }
                }
            }

            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredFoods.clear()
            filteredFoods.addAll(filterResults.values as ArrayList<FoodList>)
            notifyDataSetChanged()
        }
    }


}