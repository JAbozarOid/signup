package com.sample.auth.view.activity

import androidx.activity.viewModels
import com.sample.auth.R
import com.sample.auth.databinding.ActivityTodoBinding
import com.sample.auth.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity :
    BaseActivity<ActivityTodoBinding, TodoViewModel>(R.layout.activity_todo) {

    override val viewModel: TodoViewModel by viewModels()

    override fun onCreated() {
        super.onCreated()
    }

}