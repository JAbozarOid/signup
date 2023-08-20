package com.sample.data.api.todo

import com.sample.data.entity.signin.SignInResultModel
import com.sample.data.entity.todo.TodoResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TodoApi {

    @GET("/todos")
    suspend fun getTodoList(@Query("userId") userId: Int): Response<List<TodoResultModel>>
}