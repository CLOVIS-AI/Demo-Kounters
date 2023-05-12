package fr.demo

interface CounterService {

	suspend fun increment(counter: CounterId)

	suspend fun decrement(counter: CounterId)

	suspend fun delete(counter: CounterId)

	suspend fun create(name: String): CounterId

	suspend fun get(counter: CounterId): Counter?

	suspend fun list(): List<CounterId>

}

class CounterServiceViaRepository(private val repository: CounterRepository): CounterService {
	override suspend fun increment(counter: CounterId) {
		return repository.increment(counter)
	}

	override suspend fun decrement(counter: CounterId) {
		return repository.decrement(counter)
	}

	override suspend fun delete(counter: CounterId) {
		return repository.delete(counter)
	}

	override suspend fun create(name: String): CounterId {
		require(name.isNotBlank()) { "Il n'est pas autorisé de créer un compteur avec un nom vide" }
		val cleanedName = name.trim()
		return repository.create(cleanedName)
	}

	override suspend fun get(counter: CounterId): Counter? {
		return repository.get(counter)
	}

	override suspend fun list(): List<CounterId> {
		return repository.list()
	}

}
