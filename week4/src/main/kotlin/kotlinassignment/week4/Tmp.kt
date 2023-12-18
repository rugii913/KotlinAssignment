package kotlinassignment.week4

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Tmp {

    @RequestMapping("")
    fun hello(): String {
        return "Hello"
    }
}