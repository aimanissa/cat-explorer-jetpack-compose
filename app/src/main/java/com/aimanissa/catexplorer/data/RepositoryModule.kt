package com.aimanissa.catexplorer.data

import com.aimanissa.catexplorer.domain.CatImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCatImageRepository(catImageRepositoryImpl: CatImageRepositoryImpl): CatImageRepository
}