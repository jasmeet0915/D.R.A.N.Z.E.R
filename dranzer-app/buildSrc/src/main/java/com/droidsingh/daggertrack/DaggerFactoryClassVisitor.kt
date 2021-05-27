package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass

internal interface DaggerFactoryClassVisitor {
    fun visit(daggerFactoryClass: DaggerFactoryClass)
}

internal class DaggerFactoryClassVisitorImpl : DaggerFactoryClassVisitor {
    override fun visit(daggerFactoryClass: DaggerFactoryClass) {
        val getMethod = daggerFactoryClass.methods.first { it.name == "get" }
        getMethod.insertBefore("long initialTime = com.droidsingh.daggertrack.DaggerTrackClocks.getUptimeMillis();")
        getMethod.insertAfter("long endTime = com.droidsingh.daggertrack.DaggerTrackClocks.getUptimeMillis();")
    }
}