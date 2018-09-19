package org.hacking.nessieproj.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://api.reimaginebanking.com"
    const val API_KEY = "d2219a5dc7a6db803ffdaac7f69ba548"

    val logging = HttpLoggingInterceptor()
    val httpClient = OkHttpClient.Builder()

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }
// set your desired log level
    //logging.level = Level.BODY

    //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
    //httpClient.addInterceptor(logging);  // <-- this is the important line!

    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()
            }
            return retrofit!!
        }
}