rootProject.name =  "car-marketplace"


pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val springframeworkBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val pluginSpringVersion: String by settings
    val pluginJpa: String by settings
    val pluginShadow: String by settings
    val bmuschkoVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false

        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.springframework.boot") version springframeworkBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        kotlin("plugin.spring") version pluginSpringVersion
        kotlin("plugin.jpa") version pluginJpa

        id("com.bmuschko.docker-java-application") version bmuschkoVersion apply false
        id("com.bmuschko.docker-spring-boot-application") version bmuschkoVersion apply false
        id("com.bmuschko.docker-remote-api") version bmuschkoVersion apply false
        id("org.openapi.generator") version openapiVersion apply false
        id("com.github.johnrengelman.shadow") version pluginShadow apply false
    }
}

//include("m1l1-hello")
include("car-marketplace-acceptance")

include("car-marketplace-api-v1-jackson")

include("car-marketplace-common")
include("car-marketplace-mappers-v1")

include("car-marketplace-lib-cor")
include("car-marketplace-biz")
include("car-marketplace-stubs")

include("car-marketplace-app-spring")
include("car-marketplace-app-kafka")


include("car-marketplace-lib-logging-common")
include("car-marketplace-lib-logging-kermit")
include("car-marketplace-lib-logging-logback")