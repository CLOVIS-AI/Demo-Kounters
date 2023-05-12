package fr.demo.http

import kotlinx.serialization.Serializable

@Serializable
class CounterDto(
	val name: String,
	val value: Int,
)

@Serializable
class CounterCreateDto(
	val name: String,
)
