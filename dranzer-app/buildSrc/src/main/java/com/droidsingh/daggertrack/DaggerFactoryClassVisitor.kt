package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass
import javassist.CtClass

internal interface DaggerFactoryClassVisitor {
    fun visit(daggerFactoryClass: DaggerFactoryClass)
}

internal class DaggerFactoryClassVisitorImpl : DaggerFactoryClassVisitor {
    override fun visit(daggerFactoryClass: DaggerFactoryClass) {
        val getMethod = daggerFactoryClass.methods.first { it.name == "get" }
        getMethod.addLocalVariable("initialTime", CtClass.longType)
        getMethod.addLocalVariable("initialCpuTime", CtClass.longType)
        getMethod.insertBefore(
            """
            long initialTime = com.droidsingh.daggertrack.DaggerTrackClocks.getUptimeMillis();
            long initialCpuTime = com.droidsingh.daggertrack.DaggerTrackClocks.getCpuTimeMillis();
        """.trimIndent()
        )
        getMethod.addLocalVariable("endTime", CtClass.longType)
        getMethod.addLocalVariable("endCpuTime", CtClass.longType)
        getMethod.insertAfter(
            """
            long endTime = com.droidsingh.daggertrack.DaggerTrackClocks.getUptimeMillis();
            long endCpuTime = com.droidsingh.daggertrack.DaggerTrackClocks.getCpuTimeMillis();
            android.util.Log.d("DaggerTrack","Total time: " + (endTime - initialTime));
            android.util.Log.d("DaggerTrack","Total On CPU time: " + (endCpuTime - initialCpuTime));
            android.util.Log.d("DaggerTrack","Total Off CPU time: " + ((endTime - initialTime) - (endCpuTime - initialCpuTime)));
        """.trimIndent()
        )
    }
}