package com.sample.data.entity.todo

import com.sample.domain.entity.todo.TodoModel

data class TodoDataModel(override var userId: Int = 0) : TodoModel
