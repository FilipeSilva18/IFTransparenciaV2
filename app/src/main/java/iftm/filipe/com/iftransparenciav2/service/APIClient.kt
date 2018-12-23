package iftm.filipe.com.iftransparenciav2.service

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient() {
    lateinit var retrofit: Retrofit

    companion object {
        @get:Synchronized
        lateinit var instance: APIClient
    }

    internal val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(Interceptor {  chain: Interceptor.Chain -> chain.proceed(chain.request()) })
            .build()

    init {
        instance = this
        instance.retrofit = Retrofit.Builder()
                .baseUrl("http://www.portaltransparencia.gov.br/")
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
    }
}