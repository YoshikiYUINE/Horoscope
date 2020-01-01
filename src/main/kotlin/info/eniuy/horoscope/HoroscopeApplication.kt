package info.eniuy.horoscope

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HoroscopeApplication

fun main(args: Array<String>) {
	runApplication<HoroscopeApplication>(*args)
}
