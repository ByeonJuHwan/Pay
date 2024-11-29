import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

allprojects {
    group = "com.dev"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        // Spring Boot 기본 의존성
        "implementation"("org.springframework.boot:spring-boot-starter-web")
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect")
        "implementation"("org.springframework.boot:spring-boot-starter-aop")

        // 테스트 의존성
        "testImplementation"("org.springframework.boot:spring-boot-starter-test")
        "testImplementation"(kotlin("test"))

        // 카프카
        "implementation"("org.springframework.kafka:spring-kafka")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.getByName<BootRun>("bootRun") {
    enabled = false
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<BootBuildImage>("bootBuildImage") {
    enabled = false
}