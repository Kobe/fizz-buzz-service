package de.kobe.fizz_buzz

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Hidden
@Controller
class MainController {

    @RequestMapping("/")
    fun swaggerUI(): String {
        return "redirect:/swagger-ui/index.html"
    }

}
