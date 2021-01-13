package ru.alexanurin.chialexanurinfinalexam.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    open val errorEvent = MutableLiveData<String?>()
    open val loadingEvent = MutableLiveData<Boolean>()

    private fun createExceptionHandler(exceptionBlock: ((Throwable) -> Unit)? = null): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            exceptionBlock?.invoke(exception)
            errorEvent.postValue(exception.message ?: "Something went wrong")
        }
    }

    //  protected доступ наследников
    protected fun launchErrorJob(
        onError: ((Throwable) -> Unit)? = null,
        jobToDo: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(createExceptionHandler(onError)) {
            try {
                loadingEvent.postValue(true)
                errorEvent.postValue(null)
                jobToDo.invoke()
            } finally {
                loadingEvent.postValue(false)
            }
        }
    }
}
