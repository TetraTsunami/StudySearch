package tech.stdy.studysearch.document

data class GeneralSettingsForm(
    val name: String, val phone: String, val pss: Boolean?,
    val desiredPeopleMin: Int, val desiredPeopleMax: Int
)
