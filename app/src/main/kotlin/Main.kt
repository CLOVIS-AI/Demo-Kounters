package fr.demo.app

import fr.demo.CounterService
import fr.demo.http.client.HttpCounterService
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.jetbrains.compose.web.renderComposable

fun main() {
	val httpClient = HttpClient {
		install(ContentNegotiation) {
			json()
		}

		install(DefaultRequest) {
			url("http://localhost:8080")
		}
	}

	counterService = HttpCounterService(httpClient)

	renderComposable(rootElementId = "root") {
		HomePage()
	}
}

lateinit var counterService: CounterService
