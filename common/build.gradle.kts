plugins {
    kotlin("jvm")
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.dev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}