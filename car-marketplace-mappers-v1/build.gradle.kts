plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":car-marketplace-api-v1-jackson"))
    implementation(project(":car-marketplace-common"))

    testImplementation(kotlin("test-junit"))
}