package com.monstarlab.signup.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monstarlab.data.entity.exception.CustomException
import com.monstarlab.data.storage.dataStore.DataStore
import com.monstarlab.domain.repository.Translator
import com.monstarlab.domain.storage.Storage
import com.monstarlab.signup.command.NavigationCommand
import com.monstarlab.signup.view.widget.snack.SnackConfig
import com.monstarlab.signup.view.widget.snack.SnackStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {


    private val ioThread = Dispatchers.IO
    private val uiThread = Dispatchers.Main

    val navigationCommand = MutableLiveData<NavigationCommand>()
    val toast = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()
    val showRedSnack = MutableLiveData<SnackConfig>()
    val showGreenSnack = MutableLiveData<SnackConfig>()
    val showDefaultSack = MutableLiveData<SnackConfig>()
    val showSnack = MutableLiveData<Pair<SnackConfig?, SnackStatus?>>()

    @Inject
    protected lateinit var storage: Storage

    @Inject
    protected lateinit var datastore: DataStore

    @Inject
    protected lateinit var translator: Translator

    //region providers
    fun fire(
        useBaseExceptionHandler: Boolean = true,
        method: suspend () -> Unit
    ): Job {
        return if (useBaseExceptionHandler) viewModelScope.launch(ioThread + defaultApiExceptionHandler) {
            method.invoke()
        }
        else viewModelScope.launch(ioThread) { method.invoke() }
    }

    private val defaultApiExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            showApiError(throwable)
        }

    protected fun runOnUiThread(method: () -> Unit) =
        viewModelScope.launch(uiThread) {
            method.invoke()
        }

    protected fun runOnIOThread(method: suspend () -> Unit) {
        viewModelScope.launch(ioThread) {
            method.invoke()
        }
    }

     fun fireAndRetry(
        snack: MutableLiveData<SnackConfig> = showRedSnack,
        method: suspend () -> Unit
    ) {

        viewModelScope.launch(uiThread) {
            try {
                method.invoke()
            } catch (e: Exception) {
                showLoading.postValue(false)
                snack.postValue(SnackConfig(e.message!!, SnackConfig.getClickedOption("Retry") {
                    fireAndRetry(snack, method)
                }))
            }
        }

    }
    //endregion

    private fun showApiError(throwable: Throwable) {
        showLoading.postValue(false)
        try {
            val exception = getException(throwable)
            val message = exception?.message
            showRedSnack.postValue(SnackConfig(message!!))
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    protected fun getException(e: Throwable): CustomException? {
        return try {
            e as CustomException
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}