package tech.stdy.studysearch.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.stdy.studysearch.document.StudentRepository
import tech.stdy.studysearch.document.StudyGroupRepository
import tech.stdy.studysearch.document.asStudent

@RestController
@RequestMapping("/api/study-groups")
class StudyGroupController(
    @Autowired private val studyGroupRepo: StudyGroupRepository,
    @Autowired private val studentRepo: StudentRepository
) {
    @GetMapping("")
    fun findAll(@AuthenticationPrincipal principal: OidcUser) =
        principal.asStudent(this.studentRepo)?.id?.let { this.studyGroupRepo.findStudyGroupsByMembersContaining(it) }
            ?.let{ list -> list.groupBy { it.classId }}
            .or404()

    // @GetMapping("/generate")
    // fun findAll(@AuthenticationPrincipal principal: OidcUser) =
    //     principal.asStudent(this.studentRepo)?.id?.let { this.studyGroupRepo.findStudyGroupsByMembersContaining(it) }
    //         ?.let{ list -> list.groupBy { it.classId }}
    //         .or404()

    @GetMapping("/{id}")
    fun findById(@AuthenticationPrincipal principal: OidcUser, @PathVariable id: String) =
        this.studyGroupRepo.findById(id)
            .filter { it.isAuthorized(principal.asStudent(this.studentRepo), this.studentRepo) }
            .or404()
}