import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    jacoco
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework:spring-web:6.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kafka
    implementation("org.apache.kafka:kafka-streams:3.3.1")
    implementation("org.springframework.kafka:spring-kafka")

    // dynamo
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.258")
    implementation("io.github.boostchicken:spring-data-dynamodb:5.2.5")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    // teste
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.12.0")

    // sqs
    implementation("io.awspring.cloud:spring-cloud-starter-aws-messaging:2.4.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
