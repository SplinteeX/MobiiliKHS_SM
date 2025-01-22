plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "MobiiliKHS_SM"
include("untitled")
include("src:main:Lab1")
findProject(":src:main:Lab1")?.name = "Lab1"
