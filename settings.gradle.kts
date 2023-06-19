rootProject.name = "car-marketplace"


pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false

        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
    }
}

//include("m1l1-hello")
include("ok-marketplace-acceptance")

include("ok-marketplace-api-v1-jackson")

include("ok-marketplace-common")
include("ok-marketplace-mappers-v1")