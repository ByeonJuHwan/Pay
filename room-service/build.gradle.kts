plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
//    id("com.palantir.docker") version "0.35.0"
}

group = "com.dev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 기본 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation(kotlin("test"))

    // mariadb
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // common
    implementation(project(":common"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}