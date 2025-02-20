import dev.aga.gradle.versioncatalogs.Generator.generate
import dev.aga.gradle.versioncatalogs.GeneratorConfig

rootProject.name = "spring-webmvc-coroutines"

plugins { id("dev.aga.gradle.version-catalog-generator") version "3.2.0" }

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }

  versionCatalogs {
    generate("springLibs"){
      fromToml("spring-springBootDependencies")
    }
    generate("kotlinxLibs") {
      fromToml("kotlinx-coroutinesBom") {
        aliasPrefixGenerator = GeneratorConfig.NO_PREFIX
      }
    }
    generate("kotlinLibs") {
      fromToml("kotlin-bom") {
        aliasPrefixGenerator = GeneratorConfig.NO_PREFIX
      }
    }
  }
}
