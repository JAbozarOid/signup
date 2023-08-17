package com.sample.domain.usecase

import kotlinx.coroutines.*

abstract class BaseUseCase<P, R> {
    var executorDispatcher = Dispatchers.Default
    protected abstract suspend fun onExecute(param: P): R

    open suspend fun execute(param: P): R {
        return withContext(executorDispatcher) { onExecute(param) }
    }

    suspend fun executeAsync(param: P): Deferred<R> {
        return withContext(executorDispatcher) {
            coroutineScope {
                return@coroutineScope async {
                    onExecute(param)
                }
            }
        }
    }
}