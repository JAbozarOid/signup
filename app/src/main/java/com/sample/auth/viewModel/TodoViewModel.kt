package com.sample.auth.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.hadilq.liveevent.LiveEventConfig
import com.sample.data.entity.todo.TodoDataModel
import com.sample.data.entity.todo.TodoResultModel
import com.sample.data.util.ApiResponse
import com.sample.data.util.NetworkUtil.safeApiCall
import com.sample.domain.usecase.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
) : BaseViewModel() {

    private val _todoResData: LiveEvent<ApiResponse<List<TodoResultModel>>> =
        LiveEvent(config = LiveEventConfig.PreferFirstObserver)
    val todoResData: LiveData<ApiResponse<List<TodoResultModel>>> get() = _todoResData
    fun requestTodoListData(todoDataModel: TodoDataModel) {
        viewModelScope.launch {
            _todoResData.value = safeApiCall {
                todoUseCase.execute(todoDataModel) as Response<List<TodoResultModel>>
            }
        }
    }

}