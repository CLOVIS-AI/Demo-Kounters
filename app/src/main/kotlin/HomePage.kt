package fr.demo.app

import androidx.compose.runtime.Composable
import fr.demo.http.client.HttpCounterService
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun HomePage(counterService: HttpCounterService) {
	H1 {
		Text("Liste des compteurs")
	}

	CounterPage(counterService)
}
