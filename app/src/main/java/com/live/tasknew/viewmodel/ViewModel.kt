package com.live.tasknew.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.live.tasknew.ImageResponseClass
import com.live.tasknew.network.ApiCallsHandler
import com.live.tasknew.repo.AuthRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ViewModel : BaseViewModel() {

    private val authRepo = AuthRepo()

    val UNUSUAL_ERROR = "Something went wrong"

    private val _imageResponse = MutableLiveData<ImageResponseClass?>()

    fun onGetImages(): LiveData<ImageResponseClass?> = _imageResponse

    fun getImages() {
        showProgress()

        viewModelScope.launch(homeExceptionHandler) {
            val imageResponse = ApiCallsHandler.safeApiCall(this@ViewModel) {
                authRepo.getImages()
            }
            hideProgress()
            _imageResponse.value = imageResponse
        }
    }


    private val homeExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        var errorMessage = throwable.message
        if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            errorMessage = "Internet is not available"
        }

        errorMessage = errorMessage ?: UNUSUAL_ERROR
        hideProgress()
        showErrorMessage(errorMessage)
    }

}