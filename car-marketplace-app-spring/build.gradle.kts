plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
    id("com.bmuschko.docker-spring-boot-application")
}

dependencies {
    val kotestVersion: String by project
    val springdocOpenapiUiVersion: String by project
    val coroutinesVersion: String by project
    val serializationVersion: String by project

    implementation("org.springframework.boot:spring-boot-starter-actuator") // info; refresh; springMvc output
    implementation("org.springframework.boot:spring-boot-starter-web") // Controller, Service, etc..
    implementation("org.springframework.boot:spring-boot-starter-websocket") // Controller, Service, etc..
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiUiVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // from models to json and Vice versa
    implementation("org.jetbrains.kotlin:kotlin-reflect") // for spring-boot app
    implementation("org.jetbrains.kotlin:kotlin-stdlib") // for spring-boot app
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    implementation(project(":car-marketplace-app-common"))

    // transport models
    implementation(project(":car-marketplace-common"))

    // v1 api
    implementation(project(":car-marketplace-api-v1-jackson"))
    implementation(project(":car-marketplace-mappers-v1"))

    // biz
    implementation(project(":car-marketplace-biz"))

    // other
    implementation(project(":car-marketplace-lib-logging-common"))
    implementation(project(":car-marketplace-lib-logging-logback"))
    implementation(project(":car-marketplace-mappers-log1"))
    implementation(project(":car-marketplace-api-log1"))

    implementation("com.sndyuk:logback-more-appenders:1.8.8")
    implementation("org.fluentd:fluent-logger:0.3.4")


    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux") // Controller, Service, etc..
    testImplementation("com.ninja-squad:springmockk:3.0.1") // mockking beans
}

tasks {
    @Suppress("UnstableApiUsage")
    withType<ProcessResources> {
        from("$rootDir/specs") {
            into("/static")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

docker {
    springBootApplication {
        baseImage.set("openjdk:17")
        ports.set(listOf(8080))
        images.set(setOf("${project.name}:latest"))
        jvmArgs.set(listOf("-XX:+UseContainerSupport"))
    }
}
