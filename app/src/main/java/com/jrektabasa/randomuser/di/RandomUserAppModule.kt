package com.jrektabasa.randomuser.di

import com.jrektabasa.randomuser.network.RandomUserInterface
import com.jrektabasa.randomuser.ui.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RandomUserAppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): RandomUserInterface {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomUserInterface::class.java)
    }

}