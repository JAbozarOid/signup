package com.sample.data.repository

import com.sample.data.api.todo.TodoApi
import com.sample.domain.entity.todo.TodoResult
import com.sample.domain.repository.TodoRepository
import retrofit2.Response
import javax.inject.Inject

class AppTodoRepository @Inject constructor(private val todoApi: TodoApi) : TodoRepository {
    override suspend fun getTodoList(userid: Int): Response<List<TodoResult>> {
        return todoApi.getTodoList(userId = userid) as Response<List<TodoResult>>
    }


}