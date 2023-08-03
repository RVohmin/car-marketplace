plugins {
    kotlin("plugin.serialization")
    kotlin("multiplatform")
}
kotlin {
    jvm {}
    linuxX64 {}
    macosX64 {}
    macosArm64 {}

    sourceSets {
        val logbackVersion: String by project

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(project(":car-marketplace-common"))
                api(project(":car-marketplace-biz"))

                // biz
                api(project(":car-marketplace-biz"))

                // logging
                api(project(":car-marketplace-lib-logging-common"))
                api(project(":car-marketplace-lib-logging-kermit"))
                api(project(":car-marketplace-mappers-log1"))
                api(project(":car-marketplace-api-log1"))

                // Stubs
                implementation(project(":car-marketplace-stubs"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                api("ch.qos.logback:logback-classic:$logbackVersion")

                // transport models
                api(project(":car-marketplace-api-v1-jackson"))
                api(project(":car-marketplace-mappers-v1"))

                // logs
                api(project(":car-marketplace-lib-logging-logback"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))

            }
        }
    }
}
