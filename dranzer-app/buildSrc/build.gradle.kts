plugins {
    kotlin("jvm") version "1.5.0"
    `java-gradle-plugin`
}

repositories {
    jcenter()
    google()
}

dependencies {
    // Transform API is contained here
    implementation("com.android.tools.build:gradle-api:4.2.1")
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.0")

    // Bytecode manipulation API, similar to Java's Reflection
    implementation("org.javassist:javassist:3.28.0-GA")
}