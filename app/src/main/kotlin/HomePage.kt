package fr.demo.app

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun HomePage() {
	H1 {
		Text("Liste des compteurs")
	}

	CounterList()
}
