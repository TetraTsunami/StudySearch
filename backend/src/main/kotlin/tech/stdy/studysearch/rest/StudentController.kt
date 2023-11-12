package tech.stdy.studysearch.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.stdy.studysearch.document.Student
import tech.stdy.studysearch.document.StudentRepository
import tech.stdy.studysearch.document.StudyGroupRepository
import tech.stdy.studysearch.document.asStudent

@RestController
@RequestMapping("/api/student")
class StudentController(
    @Autowired private val studyGroupRepo: StudyGroupRepository,
    @Autowired private val studentRepo: StudentRepository
) {
    @GetMapping("/{id}")
    fun findByLogin(@AuthenticationPrincipal principal: OidcUser, @PathVariable id: String): Any {
        val thisStudentId = principal.asStudent(this.studentRepo)?.id

        return this.studentRepo.findById(id)
            .map { if (thisStudentId == it.id) it else Student(it.id, null, it.availability, it.lookingForGroup, it.classes, it.other) }
            .or404()
    }
}