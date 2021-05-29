package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import org.junit.Test
import java.io.File

internal class CtClassTransformationsTest {
    @Test
    fun `it filters out only dagger factory classes`() {
        // given
        val appCompatActivity = mock<CtClass>()
        val daggerFactoryClass = mock<CtClass>()
        val activityClass = mock<CtClass>()
        val presenterDaggerFactoryClass = mock<CtClass>()
        val presenterClass = mock<CtClass>()
        val ctMethods = arrayOf(mock<CtMethod>(), mock())
        val factoryClassName =
            "com.developers.dranzer.di.DevicesModule_ProvidesDevicePresenterFactory"
        whenever(activityClass.superclass).thenReturn(appCompatActivity)
        whenever(presenterClass.superclass).thenReturn(null)
        whenever(presenterDaggerFactoryClass.superclass).thenReturn(daggerFactoryClass)
        whenever(activityClass.superclass.name).thenReturn(
            "androidx.appcompat.app.AppCompatActivity"
        )
        whenever(presenterDaggerFactoryClass.superclass.name).thenReturn(DAGGER_FACTORY)
        whenever(presenterDaggerFactoryClass.name).thenReturn(factoryClassName)
        whenever(presenterDaggerFactoryClass.methods).thenReturn(ctMethods)
        val ctClassList = arrayListOf(
            activityClass,
            presenterDaggerFactoryClass,
            presenterClass
        )

        // when
        val daggerFactoryClassList = ctClassList.mapToDaggerFactoryClass()

        // then
        assertThat(daggerFactoryClassList).containsExactly(
            DaggerFactoryClass(factoryClassName, ctMethods)
        )
    }

    @Test
    fun `it filters out dagger components`() {
        // given
        val classPool = ClassPool.getDefault()
        classPool.makeClass("android.app.Application")
        classPool.makeClass("com.developers.dranzer.app.DranzerApp")
        classPool.makeClass("dagger.BindsInstance")
        classPool.makeClass("dagger.Component")
        classPool.makeClass("javax.inject.Singleton")
        classPool.makeClass("dagger.Component.Builder")
        val applicationComponent = File(
            "./src/test/resources/com/developers/dranzer/di" +
                    "/components/ApplicationComponent.class"
        )
        val appComponent = mock<CtClass>()
        val memberInjectorClass = mock<CtClass>()
        val factoryClass = mock<CtClass>()
        val activityClass = mock<CtClass>()
        val appComponentInterface = arrayOf(classPool.makeClass(applicationComponent.inputStream()))
        val memberInjectorInterface = arrayOf(classPool.makeClass("dagger.MembersInjector"))
        val factoryInterface = arrayOf(classPool.makeClass("dagger.internal.Factory"))
        whenever(appComponent.interfaces).thenReturn(appComponentInterface)
        whenever(memberInjectorClass.interfaces).thenReturn(memberInjectorInterface)
        whenever(factoryClass.interfaces).thenReturn(factoryInterface)
        whenever(activityClass.interfaces).thenReturn(arrayOf())
        whenever(appComponent.name).thenReturn("DaggerApplicationComponent")
        val ctClassList = listOf(
            appComponent,
            memberInjectorClass,
            factoryClass,
            activityClass
        )

        // when
        val daggerComponents = ctClassList.filterDaggerComponents()

        // then
        val daggerComponentNames = daggerComponents.map { it.name }
        assertThat(daggerComponentNames).containsExactly("DaggerApplicationComponent")
    }
}