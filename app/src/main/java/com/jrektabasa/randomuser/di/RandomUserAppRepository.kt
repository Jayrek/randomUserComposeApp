package com.jrektabasa.randomuser.di

import com.jrektabasa.randomuser.data.repository.get_user_by_count.GetUserByCountRepository
import com.jrektabasa.randomuser.data.repository.get_user_by_count.impl.GetUserByCountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RandomUserAppRepository {

    @Singleton
    @Binds
    abstract fun bindsGetUserByCountRepository(
        getUserByCountRepository: GetUserByCountRepositoryImpl
    ): GetUserByCountRepository
}