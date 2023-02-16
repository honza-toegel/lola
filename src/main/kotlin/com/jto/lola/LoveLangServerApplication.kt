package com.jto.lola

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
	"com.jto.lola",
)
@EntityScan("com.jto.lola")
class LoveLangServerApplication

fun main(args: Array<String>) {
	runApplication<LoveLangServerApplication>(*args)
}
