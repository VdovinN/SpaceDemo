package com.vdovin.spacedemo.presentation.activity.di

import com.vdovin.spacedemo.data.repository.SpaceRepository
import com.vdovin.spacedemo.usecase.GetSpaceDetailsUseCase
import com.vdovin.spacedemo.usecase.GetSpacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetSpaces(spaceRepository: SpaceRepository) = GetSpacesUseCase(spaceRepository)

    @Provides
    fun provideGetSpaceDetails(spaceRepository: SpaceRepository) =
        GetSpaceDetailsUseCase(spaceRepository)
}