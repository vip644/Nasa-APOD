package com.vipin.nasaapod.di.modules

import com.squareup.moshi.Moshi
import com.vipin.nasaapod.request.ApiService
import com.vipin.nasaapod.request.ApodJsonAdapter
import com.vipin.nasaapod.utils.Constants
import com.vipin.nasaapod.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by vipin.c on 17/09/2019
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiKey(): String {
        return Constants.KEY
    }

    @Provides
    @Singleton
    fun profvideHeaderAuthorizationInterceptor(apiKey: String): Interceptor = Interceptor {
        val originalRequest = it.request()
        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        it.proceed(newRequest)
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(ApodJsonAdapter()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(headerAuthorizationInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshiConverterFactory: MoshiConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApodApiRestService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}