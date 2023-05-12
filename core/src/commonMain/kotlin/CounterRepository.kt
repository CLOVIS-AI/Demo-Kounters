package fr.demo

interface CounterRepository {

	suspend fun increment(counter: CounterId)

	suspend fun decrement(counter: CounterId)

	suspend fun delete(counter: CounterId)

	suspend fun create(name: String): CounterId

	suspend fun get(counter: CounterId): Counter?

	suspend fun list(): List<CounterId>

}
