package fr.demo.http.client

import fr.demo.Counter
import fr.demo.CounterId
import fr.demo.CounterService
import fr.demo.http.CounterCreateDto
import fr.demo.http.CounterDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class HttpCounterService(private val httpClient: HttpClient) : CounterService {
	override suspend fun increment(counter: CounterId) {
		httpClient.put("/counters/$counter/increment")
	}

	override suspend fun decrement(counter: CounterId) {
		httpClient.put("/counters/$counter/decrement")
	}

	override suspend fun delete(counter: CounterId) {
		httpClient.delete("/counters/$counter")
	}

	override suspend fun create(name: String): CounterId {
		return httpClient.post("/counters") {
			contentType(ContentType.Application.Json)
			setBody(CounterCreateDto(name))
		}.body<String>()
	}

	override suspend fun get(counter: CounterId): Counter? {
		return httpClient.get("/counters/$counter") {
			contentType(ContentType.Application.Json)
			accept(ContentType.Application.Json)
		}.body<CounterDto?>()
			?.let { Counter(it.name, it.value) }
	}

	override suspend fun list(): List<CounterId> {
		return httpClient.get("/counters") {
			accept(ContentType.Application.Json)
		}.body()
	}

}
