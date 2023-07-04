package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val taskId = intent.getIntExtra(TASK_ID, -1)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        detailTaskViewModel.setTaskId(taskId)
        detailTaskViewModel.task.observe(this, Observer(this::showDetailTask))

        findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }
    }

    private fun showDetailTask(task: Task?) {
        if (task != null) {
            findViewById<TextInputEditText>(R.id.detail_ed_title).setText(task.title)
            findViewById<TextInputEditText>(R.id.detail_ed_description).setText(task.description)
            findViewById<TextInputEditText>(R.id.detail_ed_due_date)
                .setText(DateConverter.convertMillisToString(task.dueDateMillis))
        }
    }
}