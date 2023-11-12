package tech.stdy.studysearch.document

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface StudentRepository : MongoRepository<Student, String> {
    fun findStudentByProfileEmail(email: String): Optional<Student>
}