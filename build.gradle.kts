import java.time.LocalDate
import java.time.format.DateTimeFormatter

plugins {
	id("net.minecraftforge.gradle") version "[6.0.24,6.2)"
}

group = "com.github.hummel"
version = LocalDate.now().format(DateTimeFormatter.ofPattern("yy.MM.dd"))

repositories {
	maven("https://maven.kosmx.dev")
	maven("https://maven.blamejared.com")
	maven("https://modmaven.dev")
	maven("https://maxanier.de/maven2/")
}

dependencies {
	minecraft("net.minecraftforge:forge:1.16.5-36.2.42")
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(8)
	}
}

minecraft {
	mappings("official", "1.16.5")

	runs {
		create("client") {
			workingDirectory(project.file("runs/client"))
		}
		create("server") {
			workingDirectory(project.file("runs/server"))
		}
	}
}

tasks {
	jar {
		finalizedBy("reobfJar")

		manifest {
			attributes(
				mapOf(
					"Manifest-Version" to "1.0",
					"Implementation-Title" to "com.github.standobyte.jojo",
					"MixinConfigs" to "mixins.jojo.json",
					"Implementation-Version" to "0.2.2",
					"Specification-Vendor" to "StandoByte",
					"Specification-Title" to "Ripples of the Past",
					"Specification-Version" to "1",
					"Implementation-Vendor" to "StandoByte"
				)
			)
		}
	}
	withType<JavaCompile>().configureEach {
		options.encoding = "UTF-8"
	}
}