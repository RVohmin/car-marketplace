rootProject.name =  "car-marketplace"


pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val springframeworkBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val pluginSpringVersion: String by settings
    val pluginJpa: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false

        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.springframework.boot") version springframeworkBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion apply false
        kotlin("plugin.spring") version pluginSpringVersion
        kotlin("plugin.jpa") version pluginJpa

        id("org.openapi.generator") version openapiVersion apply false
    }
}

//include("m1l1-hello")
include("car-marketplace-acceptance")

include("car-marketplace-api-v1-jackson")

include("car-marketplace-common")
include("car-marketplace-mappers-v1")

include("car-marketplace-stubs")
include("car-marketplace-biz")

include("car-marketplace-app-spring")