plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta8"
    id("io.freefair.lombok") version "8.12.1"
}

group = "dev.willem.jobitems"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.devs.beer")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
    implementation("commons-io:commons-io:2.18.0")
    compileOnly("dev.lone:api-itemsadder:4.0.2-beta-release-11")
    compileOnly("org.spongepowered:configurate-yaml:4.1.2")
    compileOnly("org.spongepowered:configurate-core:4.1.2")
}
