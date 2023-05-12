plugins {
	kotlin("jvm")
}

dependencies {
	implementation(projects.core)

	testImplementation(Kotlin.test.junit)
	testImplementation(KotlinX.coroutines.test)
}
