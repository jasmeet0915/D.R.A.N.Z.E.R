package com.developers.dranzer.di

import com.developers.dranzer.di.scopes.PerActivity
import com.developers.dranzer.ui.DranzerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [DranzerServicesModule::class])
    abstract fun dranzerActivity(): DranzerActivity
}