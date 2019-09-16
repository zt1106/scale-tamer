plugins {
    kotlin("multiplatform") version "1.3.50"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.3.50"
}

group = "cc.zengtian"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}


kotlin {
    val javaFx = jvm("javaFx") {
        @Suppress("UNUSED_VARIABLE") val main by compilations.getting {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.13.0")
                implementation("com.github.aakira:napier:0.0.8")
            }
        }
        @Suppress("UNUSED_VARIABLE") val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        javaFx.compilations["main"].defaultSourceSet {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("no.tornado:tornadofx:1.7.17")
                implementation("org.apache.xmlgraphics:batik-svg-dom:1.11")
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.50")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.13.0")
                implementation("com.github.aakira:napier-jvm:0.0.8")
            }
        }
        javaFx.compilations["test"].defaultSourceSet {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}