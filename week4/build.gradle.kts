import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlinassignment.kotlin-application-conventions")
    id("org.springframework.boot") version "3.1.6"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.spring") version "1.8.22"
}

group = "kotlinassignment"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    // Define the main class for the application.
    mainClass.set("kotlinassignment.week4.Week4ApplicationKt")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}

tasks.register("prepareKotlinBuildScriptModel") {}
