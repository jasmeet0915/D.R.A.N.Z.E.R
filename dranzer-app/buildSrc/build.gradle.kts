import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    // Transform API is contained here
    implementation("com.android.tools.build:gradle-api:4.2.1")

    // Bytecode manipulation API, similar to Java's Reflection
    implementation("org.javassist:javassist:3.28.0-GA")
}