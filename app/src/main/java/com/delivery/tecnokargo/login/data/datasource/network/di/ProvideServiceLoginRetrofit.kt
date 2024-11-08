package com.delivery.tecnokargo.login.data.datasource.network.di

import com.delivery.tecnokargo.core.di.network.NetworkModule
import com.delivery.tecnokargo.login.data.datasource.network.ILoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object LoginServiceModule {

    @Provides
    fun provideServicesRetrofit(retrofit: Retrofit): ILoginService {
        return retrofit.create(ILoginService::class.java)
    }
}