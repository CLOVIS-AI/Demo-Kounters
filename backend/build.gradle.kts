plugins {
	kotlin("jvm")
	application
}

dependencies {
	implementation(projects.core)
	implementation(projects.httpServer)
	implementation(projects.mongo)

	implementation(Ktor.server.core)
	implementation(Ktor.server.netty)
	implementation(Ktor.server.contentNegotiation)
	implementation(Ktor.plugins.serialization.kotlinx.json)

	implementation("ch.qos.logback:logback-classic:_")
	implementation(Ktor.server.callLogging)

	implementation(Ktor.server.cors)
}

application {
	mainClass.set("fr.demo.backend.MainKt")
}
