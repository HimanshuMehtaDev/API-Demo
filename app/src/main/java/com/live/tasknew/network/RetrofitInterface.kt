package com.live.tasknew.network
import com.live.tasknew.ImageResponseClass
import retrofit2.http.GET


interface RetrofitInterface {

    @GET("users")
    suspend fun getImages(): ImageResponseClass
}
