plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
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
    implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin 리플렉션
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))

    // h2
    runtimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}