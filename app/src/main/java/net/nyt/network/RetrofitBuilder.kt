package net.nyt.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TAG = "RetrofitBuilder"
    val instant: Apis by lazy {

        val retrofit = Retrofit.Builder().baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        //create retrofit clint
        return@lazy retrofit.create(Apis::class.java)

    }
    private val logging: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient =
        OkHttpClient.Builder().apply {
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            connectTimeout(5, TimeUnit.MINUTES)
        }.addInterceptor(logging).addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder =
                originalRequest.newBuilder()
            chain.proceed(requestBuilder.build())
        }.build()
}