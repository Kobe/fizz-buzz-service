package de.kobe.fizz_buzz

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import springfox.documentation.annotations.ApiIgnore

@ApiIgnore
@Controller
class MainController {

    @RequestMapping("/")
    fun swaggerUI(): String {
        return "redirect:/swagger-ui/"
    }

}
