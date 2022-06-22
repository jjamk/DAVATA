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

class ExerListAdapter(var Exer: ArrayList<ExerList>, var con: Context) :
    RecyclerView.Adapter<ExerListAdapter.ViewHolder>(), Filterable {
    var TAG = "ExerListAdapter"

    var filteredExers = ArrayList<ExerList>()
    var itemFilter = ItemFilter()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var e_name: TextView
        var choice: Button

        init {
            e_name = itemView.findViewById(R.id.e_name)
            choice = itemView.findViewById(R.id.choice)
            choice.setOnClickListener {
                //intent사용하여 text값 넘기고 칼로리 합쳐서 표시할 것
            }
        }
    }

    init {
        filteredExers.addAll(Exer)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_exerrv, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exer: ExerList = filteredExers[position]
        //[수정요함] 이미지 작업의 경우 glide를 사용해 server의 image를 불러올 것
        //holder.iv_person_phone_book_list_item
        holder.e_name.text = "종목명 = "+"{ " + exer.exername + " 분당 칼로리소모 = " + "{ " +exer.exercal
    }

    override fun getItemCount(): Int {
        return filteredExers.size
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
            val filteredList: ArrayList<ExerList> = ArrayList<ExerList>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = Exer
                results.count = Exer.size

                return results
                //빈칸이 아닐경우 filter검색
            } else if (filterString.trim { it <= ' ' }.isNotEmpty()) {
                for (fd in Exer) {
                    if (fd.exername.contains(filterString)) {
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
            filteredExers.clear()
            filteredExers.addAll(filterResults.values as ArrayList<ExerList>)
            notifyDataSetChanged()
        }
    }


}