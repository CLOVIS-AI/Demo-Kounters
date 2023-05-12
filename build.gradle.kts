
plugins {
	kotlin("multiplatform") apply false
	kotlin("jvm") apply false
	kotlin("js") apply false
}

allprojects {
	repositories {
		mavenCentral()
	}
}
