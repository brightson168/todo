package com.example.todo


import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var rvTodo : RecyclerView
    private lateinit var appBar : Toolbar
    private lateinit var imgAdd : AppCompatImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBar = findViewById(R.id.appBar)
        imgAdd = findViewById(R.id.imgAdd)
        rvTodo = findViewById(R.id.rvTodo)

        setSupportActionBar(appBar)
        supportActionBar?.apply {
            elevation = 10F
            imgAdd.setOnClickListener {
                Toast.makeText(this@MainActivity, "imageclick", Toast.LENGTH_SHORT).show()
                addTodo(todoAdapter)
            }
        }
        todoAdapter = TodoAdapter(mutableListOf())
        rvTodo.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(null)
            addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.VERTICAL))
        }


    }


    private fun addTodo(todoAdapter: TodoAdapter) {

        val builder = AlertDialog.Builder(this)
        val input = EditText(this)
        input.hint = "Todo........"
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setTitle("Add Task")
        builder.apply {
            create()
            setMessage("What you want to do next?")
            setView(input)

            setPositiveButton("ADD",DialogInterface.OnClickListener { dialogInterface, i ->
                if(input.text.isNotEmpty()){
                    val todo = TodoModel(input.text.toString())
                    todoAdapter.addTodo(todo)
                }
            })


            setNegativeButton("CANCEL",DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
            show()
        }

    }
}