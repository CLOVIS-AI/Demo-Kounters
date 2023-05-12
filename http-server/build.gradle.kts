plugins {
	kotlin("jvm")
	kotlin("plugin.serialization")
}

dependencies {
	implementation(projects.core)
	implementation(projects.http)

	api(Ktor.server.core)
	testImplementation(Ktor.server.testHost)
	testImplementation(Ktor.server.contentNegotiation)
	testImplementation(Ktor.client.contentNegotiation)
	testImplementation(Ktor.plugins.serialization.kotlinx.json)
	testImplementation(projects.httpClient)
	testImplementation(projects.mongo)

	testImplementation("ch.qos.logback:logback-classic:_")
	testImplementation(Ktor.client.logging)

	testImplementation(Kotlin.test.junit)
	testImplementation(KotlinX.coroutines.test)
}
