import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.3.8"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("org.teamvoided.iridium") version "3.1.9"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "ender_relay desc"

val modid = property("modid") as String

repositories {
    mavenCentral()
    maven("https://maven.nucleoid.xyz")
}

modSettings {
    modId(modid)
    modName("Ender Relay")

    entrypoint("main", "com.theendercore.ender_relay.EnderRelay::commonInit")
}

val polymer_version = property("polymer_version") as String
val factory_tools = property("factory_tools") as String
dependencies {
    modImplementation("eu.pb4:polymer-autohost:${polymer_version}")
    modImplementation("eu.pb4:polymer-core:${polymer_version}")
    modImplementation("eu.pb4:polymer-blocks:${polymer_version}")
    modImplementation("eu.pb4:polymer-resource-pack:${polymer_version}")
    modImplementation("eu.pb4:polymer-virtual-entity:${polymer_version}")
    modImplementation(include("eu.pb4:factorytools:${factory_tools}")!!)

}

tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}