plugins {
    kotlin("multiplatform") version "1.3.72"
    id("maven-publish")
}
repositories {
    mavenCentral()
}
group = "io.smallibs.kraft"
version = "0.0.1"

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.3.0")
    }
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")

kotlin {
    jvm()
    js().nodejs()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation("io.kotest:kotest-runner-junit5-jvm:4.1.2")
                implementation("io.kotest:kotest-assertions-core-jvm:4.1.2")
                implementation("io.kotest:kotest-property-jvm:4.1.2")
                implementation("io.kotest:kotest-runner-console-jvm:4.1.2")
                implementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        tasks.named<Test>("jvmTest") {
            useJUnitPlatform()
        }
    }
}
