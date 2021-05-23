package com.developers.dranzer.di

import android.app.Application
import android.content.Context
import com.developers.dranzer.MqttManager
import com.developers.dranzer.MqttManagerImpl
import com.developers.dranzer.SchedulersProvider
import com.developers.dranzer.SchedulersProviderImpl
import com.developers.dranzer.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @ApplicationContext
    @Singleton
    abstract fun provideApplicationContext(application: Application): Context

    @Binds
    @Reusable
    abstract fun providesSchedulersProviders(
        schedulersProviderImpl: SchedulersProviderImpl
    ): SchedulersProvider
}