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
    val availability: WeekSchedule?,
    // TODO Filter by this when group searching and add toggle on frontend/API
    val lookingForGroup: Boolean,
    val classes: List<StudentClass>?,
    val other: MiscStudentData?
)

fun OidcUser.asStudent(studentRepo: StudentRepository) = this.getClaimAsString("email")
    ?.let { studentRepo.findStudentByProfileEmail(it).orElse(null) }

data class Profile(val name: String, val phoneNumber: String, val email: String)

typealias WeekSchedule = Map<Day, List<DaySchedule>>

enum class Day {
    SUN,
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT
}

data class DaySchedule(val start: LocalDateTime, val end: LocalDateTime)

data class StudentClass(val id: String, val displayName: String, val location: String, val section: String, val schedule: WeekSchedule)

data class MiscStudentData(val preferSameSection: Boolean, val desiredHeadCountMin: Int, val desiredHeadCountMax: Int)