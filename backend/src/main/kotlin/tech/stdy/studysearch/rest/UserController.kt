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

    @PostMapping("/settings/general")
    fun updateGeneralSettings(
        @ModelAttribute generalSettingsForm: GeneralSettingsForm,
        @AuthenticationPrincipal principal: OidcUser
    ): Any {
        var student = principal.asStudent(this.studentRepo) ?: return ApiHttpResponse.RESOURCE_NOT_FOUND

        // this.studentRepo.ins

        return ApiHttpResponse.SUCCESS
    }
}