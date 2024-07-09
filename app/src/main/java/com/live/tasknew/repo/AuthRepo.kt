package com.live.tasknew.repo


import com.live.tasknew.network.RetrofitInterface
import com.live.tasknew.network.retrofitService

class AuthRepo {

    private var retrofitInterface: RetrofitInterface = retrofitService

    suspend fun getImages() = retrofitInterface.getImages()
}