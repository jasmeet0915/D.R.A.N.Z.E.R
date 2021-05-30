plugins {
    kotlin("jvm") version "1.5.0"
    `java-gradle-plugin`
}

repositories {
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    // Transform API is contained here
    implementation("com.android.tools.build:gradle-api:4.2.1")
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.0")
    // Bytecode manipulation API, similar to Java's Reflection
    implementation("org.javassist:javassist:3.28.0-GA")

    testImplementation("junit:junit:4.13.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.mockito:mockito-inline:3.1.0")
    testImplementation("org.mockito:mockito-core:3.5.10")
}