package src

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HrUserApplication

fun main(args: Array<String>) {
    runApplication<HrUserApplication>(*args)
}
