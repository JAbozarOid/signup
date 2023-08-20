package com.sample.domain.repository

import com.sample.domain.entity.todo.TodoResult
import retrofit2.Response

interface TodoRepository {

    suspend fun getTodoList(userid : Int) : Response<List<TodoResult>>
}