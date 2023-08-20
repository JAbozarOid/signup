package com.sample.auth.view.fragment.todo

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.auth.R
import com.sample.auth.adapter.TodoAdapter
import com.sample.auth.command.NavigationCommand
import com.sample.auth.databinding.FragmentSplashBinding
import com.sample.auth.databinding.FragmentTodoBinding
import com.sample.auth.view.activity.SignupActivity
import com.sample.auth.view.fragment.BaseFragment
import com.sample.auth.viewModel.SplashViewModel
import com.sample.auth.viewModel.TodoViewModel
import com.sample.data.entity.todo.TodoDataModel
import com.sample.data.entity.todo.TodoResultModel
import com.sample.data.util.ApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class TodoFragment :
    BaseFragment<FragmentTodoBinding, TodoViewModel>(R.layout.fragment_todo) {

    override val viewModel: TodoViewModel by activityViewModels()
    private var id: Int = 0
    private lateinit var todoAdapter: TodoAdapter

    override fun initLayout(view: View) {
        super.initLayout(view)

        activity?.intent?.extras?.let {
            id = it.getInt("id")
            requestTodoListData(userId = id)
        }

        observeRemoteTodoListResData()
    }

    private fun requestTodoListData(userId: Int) {
        showMainLoadingState()
        viewModel.requestTodoListData(TodoDataModel().apply {
            this.userId = userId
        })
    }

    private fun observeRemoteTodoListResData() {
        viewModel.todoResData.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Error -> {
                    hideMainLoadingState()
                    Log.d("ABOZAR", "Error: ${it.errorMessage}")
                }

                is ApiResponse.ErrorTryAgain -> {
                    hideMainLoadingState()
                    Log.d("ABOZAR", "Error Try: ${it.errorMessage}")
                }

                is ApiResponse.Loading -> {
                    showMainLoadingState()
                }

                is ApiResponse.Success -> {
                    hideMainLoadingState()
                    bindingDataToRecView(it.data)
                }
            }
        }
    }

    private fun bindingDataToRecView(todoList : List<TodoResultModel>) {
        todoAdapter = TodoAdapter(context,todoList)
        viewBinding.rvTodo.apply {
            this.adapter = todoAdapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    private fun showMainLoadingState() {
        if (viewBinding.loading.visibility != View.VISIBLE) {
            viewBinding.loading.visibility = View.VISIBLE
        }
        viewBinding.tvLoadingText.visibility = View.VISIBLE
    }

    private fun hideMainLoadingState() {
        viewBinding.loading.visibility = View.INVISIBLE
        viewBinding.tvLoadingText.visibility = View.GONE
    }
}