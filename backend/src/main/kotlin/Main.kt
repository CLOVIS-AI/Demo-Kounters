package fr.demo.backend

import fr.demo.CounterServiceViaRepository
import fr.demo.http.server.counterRoutes
import fr.demo.mongo.MongoCounterRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import org.slf4j.event.Level

fun main() = EngineMain.main(emptyArray())

fun Application.kounters() {
	install(ContentNegotiation) {
		json()
	}

	install(CallLogging) {
		level = Level.INFO
	}

	install(CORS) {
		anyHost()
		allowMethod(HttpMethod.Post)
		allowMethod(HttpMethod.Put)
		allowMethod(HttpMethod.Delete)
		allowHeader(HttpHeaders.ContentType)
		allowHeader(HttpHeaders.Accept)
	}

	val counterRepository = MongoCounterRepository()
	val counterService = CounterServiceViaRepository(counterRepository)

	routing {
		counterRoutes(counterService)
	}
}
