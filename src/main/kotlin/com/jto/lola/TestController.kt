package com.jto.lola

import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@RestController
@RequestMapping("")
@CrossOrigin(origins = ["http://localhost:8081"])
class TestController {
    @GetMapping("/")
    fun getMessages() = "test"
}