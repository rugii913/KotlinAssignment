import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("plugin.noarg") version "1.9.21"
    kotlin("kapt") version "1.9.21" // @Entity 같은 어노테이션이 붙은 것을 분석해서 Java로 된 QueryDSL에 알려주는 역할
}

group = "com.teamsparta"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val queryDslVersion = "5.0.0"
val kotestVersion = "5.5.5"
val mockkVersion = "1.13.8"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // springdoc-openapi (참고 https://springdoc.org) - Swagger 사용하기 위함, localhost:8080/swagger-ui/index.html
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // data-jpa - DB 통신 위한 라이브러리 - transaction 포함
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // H2 database
    // implementation("com.h2database:h2")

    // PostgreSQL
    runtimeOnly("org.postgresql:postgresql")

    // AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // JWT 발급 및 검증 - 널리 쓰이고, 최신 스펙 대부분 지원하는 jjwt 사용
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // Kotlin Annotation Processing Tool
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")

    // 테스트 관련
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3") // kotest-extensions-spring과 kotest의 호환성 문제로 추가할 때 버전 잘 따져야함
    testImplementation("io.mockk:mockk:$mockkVersion")
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
