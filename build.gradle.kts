plugins {
    kotlin("jvm") version "2.1.0-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "kawaii.nahida"
version = "1.0"
description = "AntiCheat Test Server Plugin."
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("org.reflections:reflections:0.9.12")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.3.0")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    minimize()
    archiveFileName.set("${project.name}-${project.version}.jar")
}

bukkit {
    name = "ACTest"
    author = "NekoCurit"
    main = "kawaii.nahida.actest.ACTestPlugin"
    apiVersion = "1.8"
    foliaSupported = true

    softDepend = listOf(
        "ProtocolLib"
    )
}