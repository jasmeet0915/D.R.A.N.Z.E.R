package com.developers.dranzer.di.components

import android.app.Application
import com.developers.dranzer.app.DranzerApp
import com.developers.dranzer.di.ActivityBindingModule
import com.developers.dranzer.di.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent {

    fun inject(dranzerApp: DranzerApp)

    @Component.Builder
    interface Builder {
        fun bindApplication(@BindsInstance application: Application): Builder
        fun build(): ApplicationComponent
    }
}