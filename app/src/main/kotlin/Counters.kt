package fr.demo.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import fr.demo.CounterService
import fr.demo.CounterViewModel

@Composable
fun CounterPage(service: CounterService) {
	val scope = rememberCoroutineScope()
	val counters = remember { CounterViewModel(service, scope) }

	CounterList(counters)

	CreateCounter(
		create = { name ->
			counters.create(name)
		}
	)
}
