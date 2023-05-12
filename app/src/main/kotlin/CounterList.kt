package fr.demo.app

import androidx.compose.runtime.*
import fr.demo.CounterId
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun CounterList() {
	var counters by remember { mutableStateOf(emptyList<CounterId>()) }

	LaunchedEffect(Unit) {
		counters = counterService.list()
	}

	for (counter in counters) {
		Counter(counter)
	}

	CreateCounter()
}

@Composable
fun CreateCounter() {
	val scope = rememberCoroutineScope()
	var name by remember { mutableStateOf("") }

	Input(InputType.Text) {
		value(name)
		onInput { name = it.value }
	}

	Button(
		{
			onClick { scope.launch { counterService.create(name) } }
		}
	) {
		Text("Créer")
	}
}
