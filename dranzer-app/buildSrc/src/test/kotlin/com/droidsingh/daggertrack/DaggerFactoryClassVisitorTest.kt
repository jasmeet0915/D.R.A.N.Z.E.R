package com.droidsingh.daggertrack

import com.droidsingh.daggertrack.models.DaggerFactoryClass
import com.google.common.truth.Truth.assertThat
import javassist.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.File

internal class DaggerFactoryClassVisitorTest {

    private val classPool = ClassPool.getDefault()

    @Before
    fun setup() {
        prepareAndroidOsClock(classPool)
        prepareDaggerTrackClock(classPool)
        prepareSchedulersProvider(classPool)
    }

    @Test
    fun `it should visit the dagger factory classes and add initial and end system clock time`() {
        // given
        val schedulersProviderFactoryClass = prepareSchedulersProvidersFactoryClass(classPool)
        val daggerFactoryClass = DaggerFactoryClass(
            schedulersProviderFactoryClass.name,
            schedulersProviderFactoryClass.declaredMethods
        )
        val expectedClassFilePath = "./src/test/resources/expected/com/" +
                "developers/dranzer/SchedulersProviderImpl_Factory.class"
        val expectedCtClass = classPool.makeClass(File(expectedClassFilePath).inputStream())

        // when
        val daggerFactoryVisitor = DaggerFactoryClassVisitorImpl()
        daggerFactoryVisitor.visit(daggerFactoryClass)

        // then
        assertThat(schedulersProviderFactoryClass.toBytecode()).isEqualTo(expectedCtClass.toBytecode())
    }

    private fun prepareAndroidOsClock(classPool: ClassPool) {
        val androidOsClockMethodBody = """
             public static final long getUptimeMillis() {
                return 123L;
            }
        """.trimIndent()
        val androidOsClock = "android.os.SystemClock"
        classPool.makeClass(androidOsClock)
            .addMethod(androidOsClockMethodBody)
    }

    private fun prepareDaggerTrackClock(classPool: ClassPool) {
        val daggerTrackClockMethodBody = """
            public static final long getUptimeMillis() {
                return android.os.SystemClock.getUptimeMillis();
            }
        """.trimIndent()
        val daggerTrackClock = "com.droidsingh.daggertrack.DaggerTrackClocks"
        classPool.makeClass(daggerTrackClock)
            .addMethod(daggerTrackClockMethodBody)
    }

    private fun prepareSchedulersProvider(classPool: ClassPool) {
        val schedulersProvider = "com.developers.dranzer.SchedulersProviderImpl"
        val makeClass = classPool.makeClass(schedulersProvider)
        val defaultConstructor = CtNewConstructor.defaultConstructor(makeClass)
        makeClass.addConstructor(defaultConstructor)
    }

    private fun prepareSchedulersProvidersFactoryClass(classPool: ClassPool): CtClass {
        val schedulersProviderFactory = "com.developers.dranzer.SchedulersProviderImpl_Factory"
        val newInstanceMethodBody = """
            public static com.developers.dranzer.SchedulersProviderImpl newInstance() {
                return new com.developers.dranzer.SchedulersProviderImpl();
            }
        """.trimIndent()
        val getSchedulersProviderBody = """
            public com.developers.dranzer.SchedulersProviderImpl get() {
                return newInstance();
            }
        """.trimIndent()
        val schedulersProviderFactoryClass = classPool.makeClass(schedulersProviderFactory)
        schedulersProviderFactoryClass.addMethod(newInstanceMethodBody)
        schedulersProviderFactoryClass.addMethod(getSchedulersProviderBody)
        return schedulersProviderFactoryClass
    }
}

private fun CtClass.addMethod(body: String) {
    addMethod(CtMethod.make(body, this))
}


