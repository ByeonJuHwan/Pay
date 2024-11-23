plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "Pay"
include("membership-service")
include("common")
include("room-service")
include("banking-service")
