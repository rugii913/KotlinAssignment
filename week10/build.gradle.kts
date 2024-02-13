import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlinassignment.kotlin-common-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.noarg")
    kotlin("kapt")
}

group = "kotlinassignment"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
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

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // spring configuration processor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // springdoc-openapi (참고 https://springdoc.org) - Swagger 사용
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // bean validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // spring data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // spring security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // jjwt(json web token)
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

    // H2 database
     implementation("com.h2database:h2")

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    // MockK
    testImplementation("io.mockk:mockk:1.13.8")
    // SpringMockK(Ninja-Squad)
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    // spring-security-test
    testImplementation("org.springframework.security:spring-security-test")

    // queryDSL
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jakarta")

    // aws
    // implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE") // 이행적이며 취약한 종속성 에러 표시
    // https://docs.awspring.io/spring-cloud-aws/docs/2.3.0/reference/html/index.html#basic-setup
    implementation("io.awspring.cloud:spring-cloud-aws-starter:3.1.0")
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.656") // 추가 안 할 경우 S3 인터페이스 등 없음 // https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-s3

    // kotlin logging
    implementation("io.github.oshai:kotlin-logging-jvm:6.0.2")
}
