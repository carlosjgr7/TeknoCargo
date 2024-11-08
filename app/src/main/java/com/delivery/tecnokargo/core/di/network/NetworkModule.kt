package com.delivery.tecnokargo.core.di.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.delivery.tecnokargo.core.data.preferences.CONSTANTS
import com.delivery.tecnokargo.core.data.preferences.LocalStorage
import com.google.android.datatransport.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Named("httpLoggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Named("chuckerLoggingInterceptor")
    fun provideChuckerLoggingInterceptor(
        @ApplicationContext context: Context,
    ): Interceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }



    @Provides
    @Named("addHeadersInterceptor")
    fun provideAddHeadersInterceptor(localStorage: LocalStorage): Interceptor {
        return Interceptor { chain ->
            val newRequest =
                chain.request()
                    .newBuilder()
                    .apply {
                        runBlocking {
//                            localStorage.getToken()?.let { token ->
//                                addHeader("Authorization", "Bearer $token")
//                            }
                        }
                    }.build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideAuthHttpClient(
        @Named("addHeadersInterceptor") addHeadersInterceptor: Interceptor,
        @Named("httpLoggingInterceptor") logInterceptor: Interceptor,
        @Named("chuckerLoggingInterceptor") chuckerInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(addHeadersInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()
    }


    @Provides
    fun provideJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideRetrofit(
        json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CONSTANTS.URLBASE)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }
}
