package com.shadows.mydindinnchallenge.data.remote

import com.shadows.mydindinnchallenge.data.models.Dish
import com.shadows.mydindinnchallenge.data.models.Promotion
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

//As the server is being mocked, this interface is not being used

interface RemoteDataSource {

    fun getDishes(): Observable<List<Dish>>

    fun getPromotions(): Observable<List<Promotion>>

   companion object{

       //here I would build the retrofit instance to make API Calls
      fun create(): RemoteDataSource {
           val httpLoggingInterceptor = HttpLoggingInterceptor()
           httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

           val okHttpClient = OkHttpClient.Builder()
               .readTimeout(20, TimeUnit.SECONDS)
               .connectTimeout(20, TimeUnit.SECONDS)
               .addInterceptor(httpLoggingInterceptor)
               .build()

           //BuildConfig.HOST gets the host base url depending on the flavor. For this assignment both flavors have the same URL

           val retrofit = Retrofit.Builder()
               .baseUrl("url")
               .client(okHttpClient)
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .addConverterFactory(ScalarsConverterFactory.create())
               .addConverterFactory(GsonConverterFactory.create())
               .build()
           return retrofit.create(RemoteDataSource::class.java)
       }
   }
}