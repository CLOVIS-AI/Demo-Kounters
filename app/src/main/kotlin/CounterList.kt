package fr.demo.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import fr.demo.CounterViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun CounterList(
	counters: CounterViewModel,
) {
	Button({
		onClick { counters.refresh() }
	}) {
		Text("⟳")
	}

	Div({
		style {
			display(DisplayStyle.Flex)
			flexDirection(FlexDirection.Column)
			alignItems(AlignItems.Start)
		}
	}) {
		Ul {
			val list by counters.counters.collectAsState()

			for ((id, counter) in list) key(counter) {
				Li {
					if (counter != null) {
						Counter(id, counter, counters)
					} else {
						Text("…")
					}
				}
			}
		}
	}
}
