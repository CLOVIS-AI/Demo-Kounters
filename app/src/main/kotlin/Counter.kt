package fr.demo.app

import androidx.compose.runtime.Composable
import fr.demo.Counter
import fr.demo.CounterId
import fr.demo.CounterViewModel
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun Counter(
	counterId: CounterId,
	counter: Counter,
	counters: CounterViewModel,
) {
	P {
		B {
			Text(counter.name)
		}

		Text(" ")
		Button({
			onClick {
				counters.delete(counterId)
			}
		}) {
			Text("×")
		}
	}

	P {
		Button({
			onClick {
				counters.decrement(counterId)
			}
		}) {
			Text("-")
		}
		Text(" ${counter.value} ")
		Button({
			onClick {
				counters.increment(counterId)
			}
		}) {
			Text("+")
		}
	}
}
