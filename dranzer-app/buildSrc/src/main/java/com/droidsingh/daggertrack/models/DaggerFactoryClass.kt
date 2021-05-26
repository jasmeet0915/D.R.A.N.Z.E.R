package com.droidsingh.daggertrack.models

import javassist.CtMethod

internal data class DaggerFactoryClass(
    val factoryClassName: String,
    val methods: Array<CtMethod>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DaggerFactoryClass

        if (factoryClassName != other.factoryClassName) return false
        if (!methods.contentEquals(other.methods)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = factoryClassName.hashCode()
        result = 31 * result + methods.contentHashCode()
        return result
    }
}