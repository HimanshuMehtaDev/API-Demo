package com.live.tasknew.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val retrofitService by lazy {
    RetrofitClient.create()
}

class RetrofitClient {
    companion object {
        fun create(): RetrofitInterface {
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient())
                .build()
            return retrofit.create(RetrofitInterface::class.java)
        }

        private fun httpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(provideHeaderInterceptor())
                .connectTimeout(30 * 3, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS).build()

        }

        private fun provideHeaderInterceptor(): Interceptor {

            return Interceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .build()
                chain.proceed(request)

            }

        }

    }

}
