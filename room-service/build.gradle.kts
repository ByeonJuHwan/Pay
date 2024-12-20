plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("plugin.spring") version "1.9.21"
    kotlin("kapt")
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
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation(kotlin("test"))
    testImplementation("org.testcontainers:testcontainers:1.19.3")
    testImplementation("org.testcontainers:junit-jupiter:1.19.3")
    testImplementation("org.testcontainers:mariadb:1.19.3")

    // mariadb
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // Querydsl 의존성 추가
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")  // jakarta 사용
    implementation("com.querydsl:querydsl-core")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    // common
    implementation(project(":common"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}