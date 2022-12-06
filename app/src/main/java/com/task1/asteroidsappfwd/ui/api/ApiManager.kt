package com.task1.asteroidsappfwd.ui.api

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.task1.asteroidsappfwd.ui.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {

    companion object {

        private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        private var retrofit: Retrofit? = null

        private fun getInstance(): Retrofit {

            if (retrofit == null) {

                val logging = HttpLoggingInterceptor { message -> Log.e("Api", message) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder().client(client).baseUrl(Constants.BASE_URL)

                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            }

            return retrofit!!
        }

        fun getApis(): WebServices {

            return getInstance().create(WebServices::class.java)
        }
    }


}