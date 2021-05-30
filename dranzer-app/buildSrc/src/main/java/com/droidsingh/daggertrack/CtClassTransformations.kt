package com.droidsingh.daggertrack

import javassist.CtClass

internal fun List<CtClass>.filterDaggerComponents(): List<CtClass> {
    return filter {
        val daggerComponentInterface = it.interfaces.filter { interfaceClass ->
            interfaceClass.hasAnnotation("dagger.Component")
        }
        daggerComponentInterface.isNotEmpty()
    }
}

internal fun CtClass.filterSubcomponents(): List<CtClass> {
    return filterNestedSubcomponents()
        .flatMap { listOf(it) + it.filterNestedSubcomponents() }
}

private fun CtClass.filterNestedSubcomponents(): List<CtClass> {
    return nestedClasses.filter {
        val subcomponentInterface = it.interfaces.filter { interfaceClass ->
            interfaceClass.hasAnnotation("dagger.Subcomponent")
        }
        subcomponentInterface.isNotEmpty()
    }
}