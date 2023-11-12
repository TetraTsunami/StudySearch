package tech.stdy.studysearch.document

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("Groups")
data class StudyGroup(
    @Id
    val id: ObjectId = ObjectId(),
    val members: List<ObjectId>
) {
    fun getStudents(studentRepo: StudentRepository) = this.members.mapNotNull { studentRepo.findById(it.toHexString()).orElse(null) }

    fun isAuthorized(student: Student?, studentRepo: StudentRepository) =
        student != null && this.getStudents(studentRepo).any { student.id == it.id }
}