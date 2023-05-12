package fr.demo

import fr.demo.mongo.MongoCounterRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class MongoCounterRepositoryTest {

	@Test
	fun scenario() = runTest {
		val db = MongoCounterRepository()

		val counterId = db.create("bonjour")

		run {
			val counter = db.get(counterId)
			assertNotNull(counter)
			assertEquals("bonjour", counter.name)
			assertEquals(0, counter.value)
		}

		db.increment(counterId)

		run {
			val counter = db.get(counterId)
			assertNotNull(counter)
			assertEquals("bonjour", counter.name)
			assertEquals(1, counter.value)
		}

		db.decrement(counterId)

		run {
			val counter = db.get(counterId)
			assertNotNull(counter)
			assertEquals("bonjour", counter.name)
			assertEquals(0, counter.value)
		}

		run {
			val list = db.list()
			assertContains(list, counterId)
		}

		db.delete(counterId)
		run {
			val list = db.list()
			assertFalse(counterId in list)
		}
	}
}
