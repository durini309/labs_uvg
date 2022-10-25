package com.durini.architecture.di

import android.content.Context
import androidx.room.Room
import com.durini.architecture.data.local.RickAndMortyDb
import com.durini.architecture.data.local.dao.CharacterDao
import com.durini.architecture.data.remote.RickAndMortyApi
import com.durini.architecture.data.repository.CharacterRepository
import com.durini.architecture.data.repository.CharacterRepositoryImpl
import com.durini.architecture.data.repository.FakeCharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyModule {

    @Provides
    @Singleton
    fun provideRickAndMortyDb(
        @ApplicationContext context: Context
    ) : RickAndMortyDb {
        return Room.databaseBuilder(
            context,
            RickAndMortyDb::class.java,
            "rickAndMortyDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(
        database: RickAndMortyDb
    ) : CharacterDao {
        return database.characterDao()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyApi(
        client: OkHttpClient
    ) : RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: CharacterDao,
        api: RickAndMortyApi
    ) : CharacterRepository {
        return CharacterRepositoryImpl(
            characterDao = dao,
            api = api
        )
    }

}