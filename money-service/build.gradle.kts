plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("plugin.spring") version "1.9.21"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.palantir.docker") version "0.35.0"
}

group = "com.dev"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot 기본 의존성
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // 테스트 의존성
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

docker {
    println(tasks.bootJar.get().outputs.files)
    // 이미지 이름
    setName("${rootProject.name.lowercase()}-${project.name.lowercase()}:${version}")
    // 어떤 Dockerfile
    setDockerfile(file("../Dockerfile"))
    // 어떤 파일들을 Dockerfile 에 복사하는가?
    files(tasks.bootJar.get().outputs.files)
    // Dockerfile 에 전달할 인자
    buildArgs (mapOf(
        "JAR_FILE" to tasks.bootJar.get().outputs.files.singleFile.name
    ))
}