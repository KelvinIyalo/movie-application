package com.kelviniyalo.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.kelviniyalo.moviesapp.BuildConfig
import com.kelviniyalo.moviesapp.common.NetworkClient
import com.kelviniyalo.moviesapp.data.local_data.DatabaseDao
import com.kelviniyalo.moviesapp.data.local_data.MoviesDatabase
import com.kelviniyalo.moviesapp.data.provider.AppConfigImpl
import com.kelviniyalo.moviesapp.data.remote_data.MoviesApiService
import com.kelviniyalo.moviesapp.data.repository.DatabaseRepositoryImpl
import com.kelviniyalo.moviesapp.data.repository.MoviesRepositoryImpl
import com.kelviniyalo.moviesapp.domain.provider.AppConfig
import com.kelviniyalo.moviesapp.domain.repository.DatabaseRepository
import com.kelviniyalo.moviesapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {

    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context) = context
    @Singleton
    @Provides
    fun provideAppConfig() = AppConfigImpl() as AppConfig

    @Provides
    fun provideMoviesApiModule(
        networkClient: NetworkClient
    ): MoviesApiService {
        return networkClient.getApiService(BuildConfig.BASE_URL)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: MoviesApiService) = MoviesRepositoryImpl(apiService) as MoviesRepository

    @Singleton
    @Provides
    fun ProvidesDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_entity_db").build()

    @Singleton
    @Provides
    fun provideMoviesEntityDao(database: MoviesDatabase) = database.databaseDao()

    @Provides
    @Singleton
    fun provideDatabaseRepo(databaseDao: DatabaseDao) =
        DatabaseRepositoryImpl(databaseDao) as DatabaseRepository
}