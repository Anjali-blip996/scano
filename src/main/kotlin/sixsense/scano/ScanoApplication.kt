package sixsense.scano

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScanoApplication

fun main(args: Array<String>) {
    runApplication<ScanoApplication>(*args)
}
