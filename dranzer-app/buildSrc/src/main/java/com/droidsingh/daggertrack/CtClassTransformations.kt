package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass
import javassist.CtClass

internal const val DAGGER_FACTORY = "dagger.internal.Factory"

internal fun List<CtClass>.mapToDaggerFactoryClass(): List<DaggerFactoryClass> {
    val daggerCtClasses = filter { it.superclass?.name == DAGGER_FACTORY }
    return daggerCtClasses.map {
        DaggerFactoryClass(it.name, it.methods)
    }
}

internal fun List<CtClass>.filterDaggerComponents(): List<CtClass> {
    return filter {
        val daggerComponentInterface = it.interfaces.filter { interfaceClass ->
            interfaceClass.hasAnnotation("dagger.Component")
        }
        daggerComponentInterface.isNotEmpty()
    }
}