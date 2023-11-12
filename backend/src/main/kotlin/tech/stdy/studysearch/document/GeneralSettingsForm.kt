package tech.stdy.studysearch.document

data class GeneralSettingsForm(
    val name: String, val phone: String, val email: String, val pss: Boolean,
    val pplMin: Int, val pplMax: Int
)
