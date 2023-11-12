package tech.stdy.studysearch.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.stdy.studysearch.ApiHttpResponse
import tech.stdy.studysearch.document.GeneralSettingsForm
import tech.stdy.studysearch.document.MiscStudentData
import tech.stdy.studysearch.document.Profile
import tech.stdy.studysearch.document.Student
import tech.stdy.studysearch.document.StudentRepository
import tech.stdy.studysearch.document.StudyGroupRepository
import tech.stdy.studysearch.document.asStudent

@RestController
@RequestMapping("/api/user")
@CrossOrigin
class UserController(
    @Autowired private val studyGroupRepo: StudyGroupRepository,
    @Autowired private val studentRepo: StudentRepository
) {
    @GetMapping("")
    fun findByLogin(@AuthenticationPrincipal principal: OidcUser) =
        principal.asStudent(this.studentRepo).or404()

    @GetMapping("/settings/missing")
    fun getMissingSettings(@AuthenticationPrincipal principal: OidcUser): Any {
        val student = principal.asStudent(this.studentRepo) ?: return ApiHttpResponse.RESOURCE_NOT_FOUND
        val missing = mutableListOf<String>()

        if (student.profile == null)
            missing.add("profile")
        if (student.classes == null)
            missing.add("classes")
        if (student.availability == null)
            missing.add("availability")

        return missing
    }

    @PostMapping("/settings/general")
    fun updateGeneralSettings(
        @ModelAttribute form: GeneralSettingsForm,
        @AuthenticationPrincipal principal: OidcUser
    ): ApiHttpResponse {
        val student = principal.asStudent(this.studentRepo) ?: return ApiHttpResponse.RESOURCE_NOT_FOUND
        val newProfile = Profile(form.name, form.phone, principal.email)
        val newMiscData = MiscStudentData(form.pss, form.desiredPeopleMin, form.desiredPeopleMax)
        val newStudent = Student(
            student.id, newProfile, student.availability, student.lookingForGroup, student.classes, newMiscData
        )

        this.studentRepo.delete(student)
        this.studentRepo.insert(newStudent)

        return ApiHttpResponse.SUCCESS
    }
}