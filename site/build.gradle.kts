plugins {
    java
    kotlin("jvm")
    id("com.eden.orchidPlugin")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    //implementation(project(":theme"))
    orchidImplementation("io.github.javaeden.orchid:OrchidCore:${Versions.ORCHID}")
    orchidImplementation("io.github.javaeden.orchid:OrchidPosts:${Versions.ORCHID}")
    //orchidImplementation("io.github.javaeden.orchid:OrchidBsDoc:${Versions.ORCHID}")
    orchidImplementation("io.github.javaeden.orchid:OrchidPages:${Versions.ORCHID}")
    orchidImplementation("io.github.javaeden.orchid:OrchidAsciidoc:${Versions.ORCHID}")
    orchidImplementation("io.github.javaeden.orchid:OrchidSourceDoc:${Versions.ORCHID}")
    testImplementation("junit", "junit", "4.12")
}

orchid {
    theme = "MateoTheme"
    baseUrl = "http://localhost:8080"

    environment = if (findProperty("env") == "prod") { "prod" } else { "debug" }
    //args = listOf("--experimentalSourceDoc")

    githubToken = if (hasProperty("github_token")) {
        property("github_token").toString()
    } else {
        System.getenv("GITHUB_TOKEN")
    }
}