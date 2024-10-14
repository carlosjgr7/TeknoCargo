package com.delivery.tecnokargo.core.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.datastore.preferences.core.Preferences




@Module
@InstallIn(SingletonComponent::class)
object AppLocalModule {
    private const val DATA_PREFERENCES = "data_preferences"

    private val Context.dataStore by preferencesDataStore(name = DATA_PREFERENCES)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideLocalStorage(dataStore: DataStore<Preferences>): LocalStorage = LocalStorage(dataStore)
}