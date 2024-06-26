package com.ostin.tradume.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.ostin.tradume.BuildConfig
import com.ostin.tradume.api.TradumeApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesTradumeApi(): TradumeApi {
        return TradumeApi(
            baseUrl = BuildConfig.TRADUME_BASE_URL
        )
    }
}
