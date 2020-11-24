package com.vdovin.spacedemo.presentation.app.di

import com.vdovin.spacedemo.data.connection.InternetConnection
import com.vdovin.spacedemo.data.repository.SpaceRepository
import com.vdovin.spacedemo.data.source.DatabaseDataSource
import com.vdovin.spacedemo.data.source.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    fun provideSpaceRepository(
            networkDataSource: NetworkDataSource,
            databaseDataSource: DatabaseDataSource,
            internetConnection: InternetConnection
    ) = SpaceRepository(networkDataSource, databaseDataSource, internetConnection)

}