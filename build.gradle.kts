plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.mckernant1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(uri("https://mvn.mckernant1.com/release"))
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.slf4j:slf4j-api:1.7.32")

    implementation("com.mckernant1.commons:kotlin-utils:0.2.2")
    implementation("com.google.code.gson:gson:2.10")

    implementation(platform("software.amazon.awssdk:bom:2.20.78"))
    implementation("software.amazon.awssdk:cloudwatch")
    implementation("software.amazon.awssdk:s3")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}
