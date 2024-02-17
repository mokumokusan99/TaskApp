package jp.techacademy.shunsuke.kino.taskapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import java.util.*

class TaskAdapter(context: Context) : BaseAdapter(), Filterable {
    private val layoutInflater: LayoutInflater
    private var taskList = mutableListOf<Task>()
    private var filteredTaskList = mutableListOf<Task>()

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return filteredTaskList.size
    }

    override fun getItem(position: Int): Any {
        return filteredTaskList[position]
    }

    override fun getItemId(position: Int): Long {
        return filteredTaskList[position].id.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false)

        val textView1 = view.findViewById<TextView>(android.R.id.text1)
        val textView2 = view.findViewById<TextView>(android.R.id.text2)

        textView1.text = filteredTaskList[position].title
        textView2.text = filteredTaskList[position].date

        return view
    }

    fun updateTaskList(taskList: List<Task>) {
        this.taskList.clear()
        this.taskList.addAll(taskList)
        filter.filter("")
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val queryString = constraint?.toString()?.toLowerCase(Locale.getDefault())

                filteredTaskList = if (queryString.isNullOrBlank()) {
                    taskList.toMutableList()
                } else {
                    taskList.filter { it.category.toLowerCase(Locale.getDefault()).contains(queryString) }
                        .toMutableList()
                }

                filterResults.values = filteredTaskList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredTaskList = results?.values as MutableList<Task>? ?: mutableListOf()
                notifyDataSetChanged()
            }
        }
    }

}

