package com.vdovin.spacedemo.presentation.app.di

import android.content.Context
import com.vdovin.spacedemo.data.connection.InternetConnection
import com.vdovin.spacedemo.data.source.NetworkDataSource
import com.vdovin.spacedemo.framework.connection.InternetConnectionImpl
import com.vdovin.spacedemo.framework.network.NetworkDataSourceImpl
import com.vdovin.spacedemo.framework.network.api.SpaceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.spacexdata.com/"

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    }

    @Provides
    fun provideSpaceApi(okHttpClient: OkHttpClient): SpaceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(SpaceApi::class.java)
    }

    @Provides
    fun provideNetworkDataSource(spaceApi: SpaceApi): NetworkDataSource =
        NetworkDataSourceImpl(spaceApi)

    @Provides
    fun provideNetworkConnection(@ApplicationContext context: Context): InternetConnection =
        InternetConnectionImpl(context)
}