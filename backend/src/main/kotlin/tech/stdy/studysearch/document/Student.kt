package tech.stdy.studysearch.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import java.time.LocalDateTime

@Document("Users")
data class Student(
    @Id
    val id: ObjectId = ObjectId(),
    val profile: Profile,
    val availability: WeekAvailability,
    val classes: List<StudentClass>
)

fun OidcUser.asStudent(studentRepo: StudentRepository) = this.getClaimAsString("email")
    ?.let { studentRepo.findStudentByProfileEmail(it).orElse(null) }

data class Profile(val name: String, val email: String)

typealias WeekAvailability = Map<Day, List<HourAvailability>>

enum class Day {
    SUN,
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT
}

data class HourAvailability(val start: LocalDateTime, val end: LocalDateTime)

data class StudentClass(val id: String, val displayName: String)