package fr.demo.app

import androidx.compose.runtime.*
import fr.demo.Counter
import fr.demo.CounterId
import org.jetbrains.compose.web.dom.Text

@Composable
fun Counter(counterId: CounterId) {
	var counter by remember { mutableStateOf<Counter?>(null) }

	LaunchedEffect(counterId) {
		counter = counterService.get(counterId)
	}

	Text(counter.toString())
}
