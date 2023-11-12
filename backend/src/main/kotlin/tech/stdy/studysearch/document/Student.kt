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
    val profile: Profile?,
    val availability: WeekSchedule?,
    // TODO Filter by this when group searching and add toggle on frontend/API
    val lookingForGroup: Boolean,
    val classes: List<StudentClass>?,
    val other: MiscStudentData?
)

fun OidcUser.asStudent(studentRepo: StudentRepository) = this.email
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
    SAT;

    companion object {
        fun parse(str: String): Day? {
            if (str.length < 3)
                return null

            return when (str.lowercase()) {
                "sunday" -> SUN
                "monday" -> MON
                "tuesday" -> TUE
                "wednesday" -> WED
                "thursday" -> THU
                "friday" -> FRI
                "saturday" -> SAT
                else -> null
            }
        }
    }
}

data class DaySchedule(val start: LocalDateTime, val end: LocalDateTime) {

    init {
        if (start.isAfter(end)) {
            throw IllegalArgumentException("Start time must be before end time")
        }
    }
    /**
     * Returns true if this HourAvailability overlaps with the other HourAvailability
     */
    fun overlaps(other: DaySchedule): Boolean {
        return this.start.isBefore(other.end) && this.end.isAfter(other.start)
    }

    /**
     * Returns the intersection of this HourAvailability and the other HourAvailability
     * If there is no intersection, returns null
     */
    fun intersection(other: DaySchedule): DaySchedule? {
        if (this.overlaps(other)) {
            return DaySchedule(
                if (this.start.isAfter(other.start)) this.start else other.start,
                if (this.end.isBefore(other.end)) this.end else other.end
            )
        }
        return null
    }

}

data class StudentClass(val id: String, val displayName: String, val location: String, val section: String, val schedule: WeekSchedule)

data class MiscStudentData(val preferSameSection: Boolean, val desiredHeadCountMin: Int, val desiredHeadCountMax: Int)
