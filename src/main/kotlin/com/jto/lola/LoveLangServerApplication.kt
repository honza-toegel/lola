package com.jto.lola

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoveLangServerApplication

fun main(args: Array<String>) {
	runApplication<LoveLangServerApplication>(*args)
}
