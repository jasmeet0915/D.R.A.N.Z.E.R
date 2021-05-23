package com.developers.dranzer.di

import com.developers.dranzer.di.scopes.PerFragment
import com.developers.dranzer.ui.devices.DeviceStateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DranzerServicesModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [DevicesModule::class])
    internal abstract fun deviceStateFragment(): DeviceStateFragment
}