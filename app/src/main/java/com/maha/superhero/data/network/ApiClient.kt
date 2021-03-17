package com.maha.superhero.data.network

import com.maha.superhero.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: Retrofit? = null
    private val mInterceptor = HttpLoggingInterceptor()

    private val okHttpClient: OkHttpClient =
        OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.MINUTES)
            .readTimeout(15, TimeUnit.MINUTES).writeTimeout(15, TimeUnit.MINUTES)
            .addInterceptor(mInterceptor).build()


    fun getService(): WebserviceInterface {
        return client.create(WebserviceInterface::class.java)
    }

    private val client: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                    .client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return this.retrofit!!
        }
}