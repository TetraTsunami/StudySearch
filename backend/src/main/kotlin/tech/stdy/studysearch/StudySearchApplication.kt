package tech.stdy.studysearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudySearchApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<StudySearchApplication>(*args)
        }
    }
}
