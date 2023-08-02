plugins {
    application
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
}

application {
    mainClass.set("ru.vohmin.marketplace.app.kafka.MainKt")
}

docker {
    javaApplication {
        mainClassName.set(application.mainClass.get())
        baseImage.set("openjdk:17")
        maintainer.set("(c) Vohmin")
//        ports.set(listOf(8080))
        val imageName = project.name
        images.set(
            listOf(
                "$imageName:${project.version}",
                "$imageName:latest"
            )
        )
        jvmArgs.set(listOf("-Xms256m", "-Xmx512m"))
    }
}

dependencies {
    val kafkaVersion: String by project
    val coroutinesVersion: String by project
    val atomicfuVersion: String by project
    val logbackVersion: String by project
    val kotlinLoggingJvmVersion: String by project
    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:atomicfu:$atomicfuVersion")

    // log
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingJvmVersion")

    // transport models
    implementation(project(":car-marketplace-common"))
    implementation(project(":car-marketplace-api-v1-jackson"))
    implementation(project(":car-marketplace-mappers-v1"))
    // logic
    implementation(project(":car-marketplace-biz"))

    testImplementation(kotlin("test-junit"))
}
