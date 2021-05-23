package com.developers.daggertrack

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation

class DaggerTrackTransform: Transform() {

    companion object {
        private const val TRANSFORM_NAME = "DAGGER_TRACK"
    }

    override fun getName(): String = TRANSFORM_NAME

    override fun isIncremental(): Boolean = false

    override fun getInputTypes() = setOf(QualifiedContent.DefaultContentType.CLASSES)

    override fun getScopes() = mutableSetOf(QualifiedContent.Scope.PROJECT)

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        TODO()
    }
}