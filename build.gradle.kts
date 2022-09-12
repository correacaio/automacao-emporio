import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jmailen.kotlinter") version "3.12.0"
    id("com.adarshr.test-logger") version "3.2.0"
}

group = "com.correacaiio.automacaoemporio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val seleniumVersion = "4.4.0"
val kotestVersion = "5.4.2"
val coroutinesVersion = "1.6.4"
val testngVersion = "7.6.1"
val webDriverManagerVersion = "5.3.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webDriverManagerVersion")
    testImplementation("org.testng:testng:$testngVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.util=ALL-UNNAMED"
    )
}
