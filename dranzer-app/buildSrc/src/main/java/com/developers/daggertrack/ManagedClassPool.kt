package com.developers.daggertrack

import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.BaseExtension
import javassist.ClassPool
import java.io.File

class ManagedClassPool(
    transformInvocation: TransformInvocation,
    android: BaseExtension
) : ClassPool() {
    init {
        val externalDepsJars = mutableListOf<File>()
        val externalDepsDirs = mutableListOf<File>()

        transformInvocation.referencedInputs.forEach { transformInput ->
            externalDepsJars += transformInput.jarInputs.map { it.file }
            externalDepsDirs += transformInput.directoryInputs.map { it.file }
        }

        val androidJar = "${android.sdkDirectory.absolutePath}/platforms/${android.compileSdkVersion}/android.jar"

        transformInvocation.inputs.forEach { transformInput ->
            transformInput.directoryInputs.forEach { inputDirectory ->

                val baseDir = inputDirectory.file
                appendSystemPath()
                insertClassPath(baseDir.absolutePath)
                insertClassPath(androidJar)
                externalDepsJars.forEach { insertClassPath(it.absolutePath) }
                externalDepsDirs.forEach { insertClassPath(it.absolutePath) }
            }
        }
    }
}