package com.developers.daggertrack

import com.android.SdkConstants.DOT_CLASS
import com.android.build.api.transform.Format
import com.android.build.api.transform.Format.DIRECTORY
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.BaseExtension
import javassist.CtClass
import javassist.expr.ExprEditor
import javassist.expr.MethodCall
import org.gradle.api.Project
import java.io.File

class DaggerTrackTransform(
    project: Project,
    private val android: BaseExtension
) : Transform() {

    private val logger = project.logger

    companion object {
        private const val TRANSFORM_NAME = "DAGGER_TRACK"
    }

    override fun getName(): String = TRANSFORM_NAME

    override fun isIncremental(): Boolean = false

    override fun getInputTypes() = setOf(QualifiedContent.DefaultContentType.CLASSES)

    override fun getScopes() = mutableSetOf(QualifiedContent.Scope.PROJECT)

    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        val allClassFiles = findAllClassFiles(transformInvocation)
        transformInvocation.outputProvider.deleteAll()
        val outputDir = transformInvocation.outputProvider.getContentLocation(
            name,
            outputTypes,
            scopes,
            DIRECTORY
        )
        logger.info("Output Directory: ${outputDir.absolutePath}")

        transformInvocation.inputs.forEach { transformInput ->
            // Ensure JARs are copied as well:
            // https://github.com/objectbox/objectbox-java/issues/817
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

        allClassFiles.forEach { clazz ->
            clazz.instrument(object : ExprEditor() {
                override fun edit(m: MethodCall) {
                    super.edit(m)
                    logger.info("Hello")
                }
            })
            logger.info("DaggerTrack ${clazz.name}")
            clazz.writeFile(outputDir.canonicalPath)
        }
    }

    override fun getReferencedScopes(): MutableSet<in QualifiedContent.Scope> =
        mutableSetOf(
            QualifiedContent.Scope.EXTERNAL_LIBRARIES,
            QualifiedContent.Scope.SUB_PROJECTS
        )

    private fun findAllClassFiles(transformInvocation: TransformInvocation): List<CtClass> {
        // Aggregate ALL class files that are eligible for replacements
        val allClassFiles = mutableListOf<CtClass>()

        // Javassist Bridge; used to look up classes & modify bytecode
        val classPool = createClassPool(transformInvocation)

        // Iterate over all inputs
        for (input in transformInvocation.inputs) {
            for (directory in input.directoryInputs) {
                val inputPath = directory.file.absolutePath
                logger.info("Input Directory: $inputPath")

                directory.file.walkTopDown()
                    .forEach {
                        if (it.absolutePath.endsWith(DOT_CLASS)) {
                            val fqcn = it.absolutePath
                                .substring(inputPath.length + 1, it.absolutePath.length - DOT_CLASS.length)
                                .replace(File.separator, ".")
                            allClassFiles += classPool.get(fqcn)
                        }
                    }
            }
        }
        return allClassFiles.toList()
    }

    private fun createClassPool(transformInvocation: TransformInvocation): ManagedClassPool {
        return ManagedClassPool(transformInvocation, android)
    }
}