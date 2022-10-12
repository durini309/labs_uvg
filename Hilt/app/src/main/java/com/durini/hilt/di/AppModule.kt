package com.durini.hilt.di

import android.app.Application
import com.durini.hilt.data.remote.MyApi
import com.durini.hilt.data.repository.MyRepository
import com.durini.hilt.data.repository.MyRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyApi() : MyApi {
        return Retrofit.Builder()
            .baseUrl("https://someapi.com")
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        myApi: MyApi,
        context: Application,
        @Named("apiKey1") apiKey: String
    ) : MyRepository {
        return MyRepositoryImpl(
            api = myApi,
            context = context
        )
    }

    @Provides
    @Singleton
    @Named("apiKey1")
    fun provideApiKey1() = "API Key 1"

    @Provides
    @Singleton
    @Named("apiKey2")
    fun provideApiKey2() = "API Key 1"

}