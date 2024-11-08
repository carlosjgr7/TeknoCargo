package com.delivery.tecnokargo.login.di

import com.delivery.tecnokargo.login.data.LoginRepositoryImplements
import com.delivery.tecnokargo.login.data.datasource.network.LoginRemoteDataSource
import com.delivery.tecnokargo.login.data.repository.ILoginRepository
import com.delivery.tecnokargo.login.domain.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {


    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginRemoteDataSource): ILoginRepository =
        LoginRepositoryImplements(dataSource)

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepository: ILoginRepository) = LoginUseCase(loginRepository)
}