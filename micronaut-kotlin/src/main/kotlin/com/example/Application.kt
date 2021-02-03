package com.example

import com.typesafe.config.ConfigFactory
import io.micronaut.runtime.Micronaut.*

object Application {

	@JvmStatic
	fun main(args: Array<String>) {
//	val settings = Settings.from(ConfigFactory.load())
//	println(settings)
		build()
			.args(*args)
			.packages("com.example")
			.start()
	}
}

