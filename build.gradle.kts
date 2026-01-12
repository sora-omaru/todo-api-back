import org.openapitools.generator.gradle.plugin.tasks.GenerateTask
import org.gradle.api.tasks.compile.JavaCompile

plugins {
    id("java")
    id("org.springframework.boot") version "4.0.1"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.2.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}
configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-h2console")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:4.0.1")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    compileOnly("io.swagger.core.v3:swagger-annotations:2.2.27")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("buildApiDoc", GenerateTask::class) {
    generatorName.set("html2")
    inputSpec.set("$rootDir/src/main/resources/api-schema.yaml")
    outputDir.set(layout.buildDirectory.dir("apidoc").get().asFile.absolutePath)
}

tasks.register("buildSpringServer", GenerateTask::class) {
    generatorName.set("spring")
    inputSpec.set("$rootDir/src/main/resources/api-schema.yaml")
    outputDir.set(layout.buildDirectory.dir("spring").get().asFile.absolutePath)
    apiPackage.set("com.example.todoapi.controller")
    modelPackage.set("com.example.todoapi.model")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "dateLibrary" to "java8"
        )
    )
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn(tasks.named("buildSpringServer"))
}

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("spring/src/main/java"))
        }
    }
}