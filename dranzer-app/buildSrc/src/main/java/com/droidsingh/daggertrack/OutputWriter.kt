package com.droidsingh.daggertrack

import com.android.build.api.transform.Format
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import javassist.CtClass
import org.gradle.api.logging.Logger

fun Transform.copyAllJars(transformInvocation: TransformInvocation) {
    transformInvocation.inputs.forEach { transformInput ->
        // Ensure JARs are copied as well:
        transformInput.jarInputs.forEach {
            it.file.copyTo(
                transformInvocation.outputProvider.getContentLocation(
                    it.name,
                    inputTypes,
                    scopes,
                    Format.JAR
                ),
                overwrite = true
            )
        }
    }
}

fun List<CtClass>.copyCtClasses(outputDir: String) {
    forEach { clazz -> clazz.writeFile(outputDir) }
}