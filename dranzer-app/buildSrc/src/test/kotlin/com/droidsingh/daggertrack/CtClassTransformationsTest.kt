package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import javassist.CtClass
import javassist.CtMethod
import org.junit.Test

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
}