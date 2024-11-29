plugins {
    kotlin("jvm")
    id("com.palantir.docker") version "0.35.0"
}

group = "com.dev"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
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