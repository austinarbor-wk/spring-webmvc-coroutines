plugins {
  java
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.spring)
  alias(libs.plugins.springBoot)
}

kotlin {
  jvmToolchain(21)
}

tasks {
  test {
    useJUnitPlatform()
  }
}

dependencies {
 implementation(springLibs.spring.springBootStarterWeb)
 implementation(springLibs.spring.springBootStarterSecurity)
 implementation(kotlinLibs.kotlinReflect)
 implementation(kotlinxLibs.kotlinxCoroutinesCore)
 implementation(kotlinxLibs.kotlinxCoroutinesReactor)
 implementation(springLibs.logback.logbackCore)

 testImplementation(libs.junit.jupiter)
 testImplementation(springLibs.spring.springBootStarterTest)
 testImplementation(kotlinxLibs.kotlinxCoroutinesTest)
}
