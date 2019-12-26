buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    kotlin("multiplatform") version "1.3.61"
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    maven { url = uri("https://kotlin.bintray.com/kotlin-js-wrappers") }
}

val ktor_version = "1.2.5"
val kotlin_react_version = "16.9.0-pre.89-kotlin-1.3.60"
val kotlin_react_redux_version = "5.0.7-pre.89-kotlin-1.3.60"
val kotlin_react_router_dom_version = "4.3.1-pre.89-kotlin-1.3.60"
val kotlin_styled_version = "1.0.0-pre.89-kotlin-1.3.60"
val logback_version = "1.2.3"

val npm_core_js_version = "3.6.0"
val npm_react_version = "16.12.0"
val npm_redux_version = "4.0.5"
val npm_react_redux_version = "7.1.3"
val npm_react_router_dom_version = "5.1.2"
val npm_styled_components_version = "^4.4.1"
val npm_inline_styled_prefixer_version = "^5.1.0"


kotlin {
    jvm()
    js {
        useCommonJs()
        browser {
        }
    }
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
                implementation("io.ktor:ktor-server-netty:$ktor_version")
                implementation("io.ktor:ktor-html-builder:$ktor_version")
                implementation("ch.qos.logback:logback-classic:$logback_version")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))

                implementation("org.jetbrains:kotlin-react:$kotlin_react_version")
                implementation("org.jetbrains:kotlin-react-dom:$kotlin_react_version")
                implementation("org.jetbrains:kotlin-react-redux:$kotlin_react_redux_version")
                implementation("org.jetbrains:kotlin-react-router-dom:$kotlin_react_router_dom_version")
                implementation("org.jetbrains:kotlin-styled:$kotlin_styled_version")

                implementation(npm("core-js", npm_core_js_version))
                implementation(npm("react", npm_react_version))
                implementation(npm("react-dom", npm_react_version))
                implementation(npm("redux", npm_redux_version))
                implementation(npm("react-redux", npm_react_redux_version))
                implementation(npm("react-router-dom", npm_react_router_dom_version))
                implementation(npm("styled-components", npm_styled_components_version))
                implementation(npm("inline-style-prefixer", npm_inline_styled_prefixer_version))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

val jsBrowserWebpack by tasks.getting(org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack::class)

tasks.named("jvmJar", Jar::class) {
    dependsOn(jsBrowserWebpack)
    from("${jsBrowserWebpack.destinationDirectory}/${jsBrowserWebpack.entry.name}")
}

val run by tasks.registering(JavaExec::class) {
    dependsOn(tasks["jvmJar"])

    group = "application"
    main = "sample.SampleJvmKt"
    args = emptyList()

    classpath(project.configurations["jvmRuntimeClasspath"], tasks["jvmJar"])
}
