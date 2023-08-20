package com.sample.domain.usecase

import com.sample.domain.entity.todo.TodoModel
import com.sample.domain.entity.todo.TodoResult
import com.sample.domain.repository.TodoRepository
import retrofit2.Response
import javax.inject.Inject

class TodoUseCase @Inject constructor(private val todoRepository: TodoRepository) :
    BaseUseCase<TodoModel, Response<List<TodoResult>>>() {


    override suspend fun onExecute(param: TodoModel): Response<List<TodoResult>> {
        return todoRepository.getTodoList(userid = param.userId)
    }
}