package info.eniuy.horoscope

import com.google.gson.JsonObject
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Controller
class HtmlController {

    val loginDate = LocalDateTime.now().toString()
    val formatDate = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now()).toString()

    @GetMapping("/")
    fun index(model: Model): String {
        println("start " + this.javaClass.canonicalName)
        //set horoscope data of the day
        val resultHoroscope: JsonObject = getHoroscope(formatDate)
        println("results data: $resultHoroscope")
        val horoscopeHtml: String = buildHoroscopeHtml(formatDate, resultHoroscope)

        model["title"] = "Hello, Spring!"
        model["horoscopeHtml"] = horoscopeHtml
        //set horoscope data to javascript
        model["horoscopeJson"] = resultHoroscope.toString()
        return "index"
    }

}
