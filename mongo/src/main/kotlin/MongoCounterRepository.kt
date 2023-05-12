package fr.demo.mongo

import fr.demo.Counter
import fr.demo.CounterId
import fr.demo.CounterRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random
import kotlin.random.nextUInt

class MongoCounterRepository : CounterRepository {
	private val lock = Mutex()
	private val data: MutableMap<CounterId, Counter> = mutableMapOf()

	override suspend fun increment(counter: CounterId) {
		lock.withLock {
			val current = data[counter] ?: return
			val new = current.copy(value = current.value + 1)
			data[counter] = new
		}
	}

	override suspend fun decrement(counter: CounterId) {
		lock.withLock {
			val current = data[counter] ?: return
			val new = current.copy(value = current.value - 1)
			data[counter] = new
		}
	}

	override suspend fun delete(counter: CounterId) {
		lock.withLock {
			data.remove(counter)
		}
	}

	override suspend fun create(name: String): CounterId {
		val id = Random.nextUInt().toString(radix = 16)
		lock.withLock {
			data[id] = Counter(
				name = name,
				value = 0,
			)
		}
		return id
	}

	override suspend fun get(counter: CounterId): Counter? {
		return lock.withLock {
			data[counter]
		}
	}

	override suspend fun list(): List<CounterId> {
		return lock.withLock {
			data.keys.toList()
		}
	}
}
