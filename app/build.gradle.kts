plugins {
	kotlin("js")
	id("org.jetbrains.compose") version "1.3.1"
}

kotlin {
	js {
		browser()
		binaries.executable()
	}
}

dependencies {
	implementation(projects.core)
	implementation(projects.httpClient)

	implementation(Ktor.client.core)
	implementation(Ktor.client.contentNegotiation)
	implementation(Ktor.plugins.serialization.kotlinx.json)

	implementation(compose.runtime)
	implementation(compose.web.core)

	implementation(devNpm("vite", "4.3.5"))
	implementation(devNpm("@originjs/vite-plugin-commonjs", "1.0.3"))
	implementation(devNpm("@rollup/plugin-commonjs", "24.1.0"))
}

//region Vite

val jsWorkspace = "${rootProject.buildDir}/js"
val jsProjectDir = "$jsWorkspace/packages/${rootProject.name}-${project.name}"

val kotlinNodeJsSetup by rootProject.tasks.getting(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsSetupTask::class)
val kotlinNpmInstall by rootProject.tasks.getting(org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask::class)

val productionExecutableCompileSync by tasks.getting(Task::class)

val configureVite by tasks.registering(Copy::class) {
	description = "Copies the Vite configuration file to the build directory"
	group = "vite"

	from("./vite.config.js")
	into(jsProjectDir)

	dependsOn(kotlinNpmInstall)
}

val vite by tasks.registering(Exec::class) {
	description = "Starts a development web server"
	group = "vite"

	workingDir = file(jsProjectDir)
	commandLine(
		kotlinNodeJsSetup.destination / "bin" / "node",
		file(jsWorkspace) / "node_modules" / "vite" / "bin" / "vite.js",
		"dev"
	)

	dependsOn(
		kotlinNodeJsSetup,
		kotlinNpmInstall,
	)
}

val developmentExecutableCompileSync: Task by tasks.getting {
	dependsOn(
		configureVite,
	)
}

val production by tasks.registering(Exec::class) {
	description = "Compiles the production web demo"
	group = "vite"

	workingDir = file(jsProjectDir)
	commandLine(
		kotlinNodeJsSetup.destination / "bin" / "node",
		file(jsWorkspace) / "node_modules" / "vite" / "bin" / "vite.js",
		"build"
	)

	dependsOn(
		kotlinNodeJsSetup,
		kotlinNpmInstall,
		configureVite,
		productionExecutableCompileSync
	)
}

operator fun File.div(child: String) = File(this, child)

//endregion
