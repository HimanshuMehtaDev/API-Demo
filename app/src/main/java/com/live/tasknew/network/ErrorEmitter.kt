package com.live.tasknew.network

interface ErrorEmitter {
    fun onError(message: String)
    fun onError(errorType: ErrorType)
}