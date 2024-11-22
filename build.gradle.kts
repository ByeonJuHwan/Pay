import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.21"  // Spring 지원을 위한 Kotlin 플러그인
    id("org.springframework.boot") version "3.2.3"  // Spring Boot 플러그인
    id("io.spring.dependency-management") version "1.1.4"  // 스프링 의존성 관리
}

group = "com.dev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 기본 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")  // Web 서버 기능
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")  // Kotlin JSON 지원
    implementation("org.jetbrains.kotlin:kotlin-reflect")  // Kotlin 리플렉션

    // 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
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