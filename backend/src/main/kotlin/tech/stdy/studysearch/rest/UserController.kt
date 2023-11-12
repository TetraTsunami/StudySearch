package tech.stdy.studysearch.rest

import com.lowagie.text.pdf.PdfReader
import com.lowagie.text.pdf.parser.PdfTextExtractor
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import tech.stdy.studysearch.document.GeneralSettingsForm
import tech.stdy.studysearch.document.MiscStudentData
import tech.stdy.studysearch.document.Profile
import tech.stdy.studysearch.document.Student
import tech.stdy.studysearch.document.StudentRepository
import tech.stdy.studysearch.document.StudyGroupRepository
import tech.stdy.studysearch.document.asStudent

@Controller
@RequestMapping("/api/user")
class UserController(
    @Autowired private val studyGroupRepo: StudyGroupRepository,
    @Autowired private val studentRepo: StudentRepository
) {
    @ResponseBody
    @GetMapping("")
    fun findByLogin(@AuthenticationPrincipal principal: OidcUser) =
        principal.asStudent(this.studentRepo).or404()

    @ResponseBody
    @GetMapping("/settings/missing")
    fun getMissingSettings(@AuthenticationPrincipal principal: OidcUser): Any {
        val student = principal.asStudent(this.studentRepo)
        val missing = mutableListOf<String>()

        if (student?.profile == null)
            missing.add("profile")
        if (student?.classes == null)
            missing.add("classes")
        if (student?.availability == null)
            missing.add("availability")

        return missing
    }

    @PostMapping("/settings/general")
    fun updateGeneralSettings(
        @ModelAttribute form: GeneralSettingsForm, @RequestParam("redirect") redirectStr: String,
        @AuthenticationPrincipal principal: OidcUser,
        redirectAttributes: RedirectAttributes
    ): String {
        val student = principal.asStudent(this.studentRepo)
        val newProfile = Profile(form.name, form.phone, principal.email)
        val newMiscData = MiscStudentData(form.pss ?: false, form.desiredPeopleMin, form.desiredPeopleMax)
        val newStudent = Student(
            student?.id ?: ObjectId(), newProfile, student?.availability, student?.lookingForGroup ?: true,
            student?.classes, newMiscData
        )

        student?.let { this.studentRepo.delete(it) }
        this.studentRepo.insert(newStudent)

        redirectAttributes.addAttribute("message", "Successfully updated classes.")

        return "redirect:${redirectStr}"
    }

    @PostMapping(path = ["/settings/classes"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updateClasses(
        @RequestParam("pdf") file: MultipartFile, @RequestParam("redirect") redirectStr: String,
        @AuthenticationPrincipal principal: OidcUser,
        redirectAttributes: RedirectAttributes
    ): String {
        val lines = mutableListOf<String>()

        PdfReader(file.bytes).use { pdfReader ->
            val textExtractor = PdfTextExtractor(pdfReader)

            for (pageNum in 2..pdfReader.numberOfPages) {
                val pageLines = textExtractor.getTextFromPage(pageNum).split('\n')
                    .filterNot { it.isEmpty() }
                    .let { it.subList(0, it.size - 2) }

                lines.addAll(pageLines)
            }
        }

        // if (lines.isEmpty())
        //     throw IllegalStateException("Lines is empty, blank PDF")
        //
        // var curDay: Day? = null
        // var relativeI = 0
        // var id: String = ""
        // var displayName: String = ""
        // var section: String = ""
        // var location: String = ""
        // var time: String = ""
        // val classMap = mutableMapOf<String, MutableList<TempClass>>()
        //
        // for (line in lines) {
        //     if (curDay == null) {
        //         curDay = Day.parse(line)!!
        //         relativeI = 0
        //         continue
        //     }
        //
        //     val day = Day.parse(line)
        //     if (day != null) {
        //         curDay = day
        //         relativeI = 0
        //         continue
        //     }
        //
        //     when (relativeI) {
        //         0 -> {
        //             val split = line.split(":").map { it.trim() }
        //             id = split[0]
        //             displayName = split[1]
        //         }
        //         1 -> {
        //             section = line
        //         }
        //         2 -> {
        //             location = line
        //         }
        //         3 -> {
        //             time = line
        //         }
        //     }
        //
        //     relativeI++
        //
        //     if (relativeI == 4) {
        //         relativeI = 0
        //
        //         val tempClass = TempClass(id, displayName, location, section, time)
        //         classMap.computeIfAbsent("$id $section") { mutableListOf() }.add(tempClass)
        //     }
        // }

        redirectAttributes.addAttribute("message", "Successfully updated classes.")

        return "redirect:${redirectStr}"
    }

    // @PostMapping(path = ["/settings/availability"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    // fun updateAvailability(
    //     @RequestParam("pdf") file: MultipartFile, @RequestParam("redirect") redirectStr: String,
    //     @AuthenticationPrincipal principal: OidcUser,
    //     redirectAttributes: RedirectAttributes
    // ): String {
    //     PdfReader(file.bytes).use { pdfReader ->
    //         val textExtractor = PdfTextExtractor(pdfReader)
    //
    //         for (pageNum in 2..pdfReader.numberOfPages) {
    //             val pageLines = textExtractor.getTextFromPage(pageNum).split('\n')
    //                 .filterNot { it.isEmpty() }
    //                 .let { it.subList(0, it.size - 2) }
    //
    //             println(pageLines)
    //         }
    //     }
    //
    //     redirectAttributes.addAttribute("message", "Successfully updated classes.")
    //
    //     return "redirect:${redirectStr}"
    // }

}

// private data class TempClass(val id: String, val displayName: String, val location: String, val section: String, val time: String)