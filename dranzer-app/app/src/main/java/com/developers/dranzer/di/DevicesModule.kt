package com.developers.dranzer.di

import android.content.Context
import com.developers.dranzer.MqttManager
import com.developers.dranzer.MqttManagerImpl
import com.developers.dranzer.SchedulersProvider
import com.developers.dranzer.data.DranzerRepository
import com.developers.dranzer.di.qualifier.ApplicationContext
import com.developers.dranzer.domain.SetDeviceStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object DevicesModule {

    @Provides
    fun providesDranzerRepository(@DeviceMqttManager mqttManager: MqttManager): DranzerRepository {
        return DranzerRepository(mqttManager)
    }

    @Provides
    fun providesSetDeviceStateUseCase(
        dranzerRepository: DranzerRepository,
        schedulersProvider: SchedulersProvider
    ): SetDeviceStateUseCase {
        return SetDeviceStateUseCase(dranzerRepository, schedulersProvider)
    }

    @Provides
    @DeviceMqttManager
    fun providesDeviceScreenMqttManager(@ApplicationContext context: Context): MqttManager {
        return MqttManagerImpl(context)
    }

    @Qualifier
    private annotation class DeviceMqttManager
}