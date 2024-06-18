plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "com.pottery"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("src/generated-snippets")
extra["springCloudVersion"] = "2023.0.2"

val asciidoctorExtensions by configurations.creating

dependencies {
    asciidoctorExtensions("io.spring.asciidoctor.backends:spring-asciidoctor-backends:0.0.7")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.asciidoctor {
    inputs.dir(project.extra["snippetsDir"]!!)
    configurations("asciidoctorExtensions")
    outputOptions {
        backends("spring-html")
    }
    baseDirFollowsSourceDir()
    sources("**/index.adoc")
    attributes(
        mapOf(
            "snippets" to file("src/generated-snippets"),
            "sourceDirectory" to "src/docs/asciidoc",
            "revnumber" to project.version
        )
    )
}

tasks.bootJar {
    dependsOn("asciidoctor")
    from ("build/docs/asciidoc") {
        into("static/docs")
    }
}

tasks.bootBuildImage {
    dependsOn("bootJar")
    imageName = "serhiibabanov/backend-gateway"
}

