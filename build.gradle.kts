plugins {
    id("java")
    id("net.serenity-bdd.serenity-gradle-plugin") version "3.9.8"
}

group = "com.automation"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val serenityCoreVersion = "4.2.9"

dependencies {
    // Serenity BDD + Screenplay + Cucumber
    testImplementation("net.serenity-bdd:serenity-core:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-cucumber:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-screenplay-rest:$serenityCoreVersion")
    testImplementation("net.serenity-bdd:serenity-rest-assured:$serenityCoreVersion")

    // JUnit 4 (requerido por Serenity con Cucumber)
    testImplementation("junit:junit:4.13.2")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.18.0")
    testImplementation("io.cucumber:cucumber-junit:7.18.0")

    // Lombok (opcional, para el modelo Pet)
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
        events("passed", "skipped", "failed")
    }
}

tasks.named("test") {
    finalizedBy("aggregate")
}