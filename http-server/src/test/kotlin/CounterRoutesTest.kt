package fr.demo.http.server

import fr.demo.CounterServiceViaRepository
import fr.demo.http.client.HttpCounterService
import fr.demo.mongo.MongoCounterRepository
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation.Plugin as ClientContentNegotiation
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation as ServerContentNegotiation

class CounterRoutesTest {

	@Test
	fun scenario() = runTest {
		testApplication {
			install(ServerContentNegotiation) {
				json()
			}

			val db = MongoCounterRepository()

			routing {
				counterRoutes(CounterServiceViaRepository(db))
			}

			val httpClient = createClient {
				install(ClientContentNegotiation) {
					json()
				}

				install(Logging) {
					level = LogLevel.BODY
				}
			}

			val client = HttpCounterService(httpClient)

			val counterId = client.create("bonjour")

			run {
				val counter = client.get(counterId)
				assertNotNull(counter)
				assertEquals("bonjour", counter.name)
				assertEquals(0, counter.value)
			}

			client.increment(counterId)

			run {
				val counter = client.get(counterId)
				assertNotNull(counter)
				assertEquals("bonjour", counter.name)
				assertEquals(1, counter.value)
			}

			client.decrement(counterId)

			run {
				val counter = client.get(counterId)
				assertNotNull(counter)
				assertEquals("bonjour", counter.name)
				assertEquals(0, counter.value)
			}

			run {
				val list = client.list()
				assertContains(list, counterId)
			}

			client.delete(counterId)
			run {
				val list = client.list()
				assertFalse(counterId in list)
			}
		}
	}
}
