package com.sample.data.entity.todo

import com.sample.domain.entity.todo.TodoResult

data class TodoResultModel(
    override var userId: Int,
    override var id: Int,
    override var title: String,
    override var completed: Boolean
) : TodoResult
