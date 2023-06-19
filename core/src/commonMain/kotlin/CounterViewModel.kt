package fr.demo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CounterViewModel(
    private val service: CounterService,
    private val scope: CoroutineScope,
) {

    private val _counters = MutableStateFlow(emptyMap<CounterId, Counter?>())
    val counters: StateFlow<Map<CounterId, Counter?>> get() = _counters

    init {
        refresh()
    }

    fun refresh() = scope.launch {
        _counters.value = emptyMap()

        val results = service.list()
            .associateWith { service.get(it) }
        _counters.value = results
    }

    fun increment(counterId: CounterId) = scope.launch {
        service.increment(counterId)
        refresh()
    }

    fun decrement(counterId: CounterId) = scope.launch {
        service.decrement(counterId)
        refresh()
    }

    fun delete(counterId: CounterId) = scope.launch {
        service.delete(counterId)
        refresh()
    }

    fun create(name: String) = scope.launch {
        service.create(name)
        refresh()
    }

}
