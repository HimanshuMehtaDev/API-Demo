package com.live.tasknew.network

interface UIEventManager {
    fun showProgress()
    fun hideProgress()
    fun showErrorMessage(error: String) : String
}