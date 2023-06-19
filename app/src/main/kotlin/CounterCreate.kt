package fr.demo.app

import androidx.compose.runtime.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text

@Composable
fun CreateCounter(
	create: (name: String) -> Unit,
) {
	var name by remember { mutableStateOf("") }

	Input(InputType.Text) {
		value(name)
		onInput { name = it.value }
	}

	Button(
		{
			onClick { create(name); name = "" }
		}
	) {
		Text("Créer")
	}
}
