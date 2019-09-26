rootProject.name = "music-theory-trainer"
pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin-multiplatform") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
            }
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "org.jetbrains.kotlin.plugin.serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:1.3.50")
            }
//            if (requested.id.id == "no.tornado.fxlauncher") {
//                useModule("gradle.plugin.no.tornado:fxlauncher-gradle-plugin:1.0.20")
//            }
            if (requested.id.id == "javafx-gradle-plugin") {
                useModule("de.dynamicfiles.projects.gradle.plugins:javafx-gradle-plugin:8.8.2")
            }
        }
    }
}

include(
    ":common",
    ":javafx"
)
