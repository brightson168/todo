package com.example.todo

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<TodoModel>
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.todo_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.apply {
            tvTodo.text = curTodo.title

            btnComplete.setOnClickListener{
                val builder = AlertDialog.Builder(itemView.context)
                builder.create()
                builder.apply {
                    setTitle("Are you Sure ?")
                    setMessage("Completed todo also delete it.")
                    setPositiveButton("YEs"){  _: DialogInterface, _: Int ->
                        deleteTodo(position)
                        Toast.makeText(itemView.context, "TODO: ${curTodo.title} is deleted", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("CANCEL"){ dialog : DialogInterface, _: Int ->
                          dialog.dismiss()

                    }
                    show()
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvTodo : TextView = itemView.findViewById(R.id.tvTodo)
        var btnComplete : Button = itemView.findViewById(R.id.btnDelete)

    }

    fun addTodo(todo: TodoModel){
        todos.add(todo)
        notifyItemInserted(todos.size -1)
    }

    private fun deleteTodo(index : Int){
        todos.removeAt(index)
        notifyDataSetChanged()
    }

}