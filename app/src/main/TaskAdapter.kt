package jp.techacademy.shunsuke.kino.taskapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TaskAdapter(context: Context) : BaseAdapter() {
    private val layoutInflater: LayoutInflater
    private var taskList = mutableListOf<String>()

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return taskList.size

    }

    override fun getItem(position: Int): Any {
        return taskList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(
                android.R.layout.simple_list_item_2,
                parent,
                false
            )

        val textView1 = view.findViewById<TextView>(android.R.id.text1)
        val textView2 = view.findViewById<TextView>(android.R.id.text2)

        // 後でTaskクラスから情報を取得するように変更する
        textView1.text = taskList[position]

        return view
    }
    fun updateTaskList(taskList: List<String>) {
        // 一度クリアしてから新しいタスク一覧に入替
        this.taskList.clear()
        this.taskList.addAll(taskList)
        // データに変更があったことをadapterに通知
        notifyDataSetChanged()
    }
}