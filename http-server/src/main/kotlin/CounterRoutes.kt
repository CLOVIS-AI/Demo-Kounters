package fr.demo.http.server

import fr.demo.CounterService
import fr.demo.http.CounterCreateDto
import fr.demo.http.CounterDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.counterRoutes(counters: CounterService) = route("/counters") {
	put("{id}/increment") {
		val id = call.parameters["id"] ?: run {
			call.respond(HttpStatusCode.UnprocessableEntity, "L'identifiant est manquant")
			return@put
		}

		counters.increment(id)

		call.respond(Unit)
	}

	put("{id}/decrement") {
		val id = call.parameters["id"] ?: run {
			call.respond(HttpStatusCode.UnprocessableEntity, "L'identifiant est manquant")
			return@put
		}

		counters.decrement(id)

		call.respond(Unit)
	}

	delete("{id}") {
		val id = call.parameters["id"] ?: run {
			call.respond(HttpStatusCode.UnprocessableEntity, "L'identifiant est manquant")
			return@delete
		}

		counters.delete(id)

		call.respond(Unit)
	}

	post {
		val body = call.receive<CounterCreateDto>()

		val created = counters.create(body.name)

		call.respond(created)
	}

	get("{id}") {
		val id = call.parameters["id"] ?: run {
			call.respond(HttpStatusCode.UnprocessableEntity, "L'identifiant est manquant")
			return@get
		}

		val counter = counters.get(id)

		if (counter != null)
			call.respond(CounterDto(counter.name, counter.value))
		else
			call.respond(HttpStatusCode.NotFound, "Compteur introuvable")
	}

	get {
		val result = counters.list()

		call.respond(result)
	}
}
