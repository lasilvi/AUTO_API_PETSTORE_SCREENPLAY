plugins {
    id("java")
    id("net.serenity-bdd.serenity-gradle-plugin") version "3.9.8"
}

// Redirigir el output de Gradle a gradle-build/ para no colisionar
// con los .class que IntelliJ indexa en build/
layout.buildDirectory.set(file("gradle-build"))

group = "com.automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val serenityCoreVersion = "4.2.9"

dependencies {

    testImplementation("net.serenity-bdd:serenity-core:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-cucumber:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay-rest:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-rest-assured:$serenityCoreVersion")

    testImplementation("junit:junit:4.13.2")

    testImplementation("io.cucumber:cucumber-java:7.18.0")
    testImplementation("io.cucumber:cucumber-junit:7.18.0")
   
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


tasks.test {
    useJUnit()
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}

tasks.named("test") {
    finalizedBy("aggregate")
}