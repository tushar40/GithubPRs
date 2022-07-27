package com.martnear.githubclosedprs.api

import com.google.gson.Gson
import com.martnear.githubclosedprs.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    /**
     * Member variables
     */

    private var api: APIInterface? = null

    /**
     * Public Methods
     */

    fun getApi(): APIInterface {
        if (api == null) {
            val retrofit = getRetrofit()
            api = retrofit.create(APIInterface::class.java)
        }

        return api!!
    }

    /**
     * Private Methods
     */

    private fun getRetrofit(): Retrofit {
        val baseURL = Constants.BASE_URL
        val client =  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(baseURL)
            .client(getHttpClient())

        return client.build()
    }

    private fun getHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        return client.build()
    }
}