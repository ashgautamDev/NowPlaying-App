package com.ashish.nowplayingapp.di

import android.content.Context
import androidx.room.Room
import com.ashish.nowplayingapp.data.local.FavMovieDao
import com.ashish.nowplayingapp.data.local.FavMoviesDatabase
import com.ashish.nowplayingapp.data.remote.MovieApi
import com.ashish.nowplayingapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // For Local Repository
    @Singleton
    @Provides
    fun provideDao(favMoviesDatabase: FavMoviesDatabase): FavMovieDao =
       favMoviesDatabase.getFavMovieDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FavMoviesDatabase =
        Room.databaseBuilder(
            context,
            FavMoviesDatabase::class.java,
            "favourites-db"
        ).fallbackToDestructiveMigration().build()


    //For Remote

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MovieApi::class.java)

}