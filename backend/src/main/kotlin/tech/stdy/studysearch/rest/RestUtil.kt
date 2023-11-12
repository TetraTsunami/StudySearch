package tech.stdy.studysearch.rest

import tech.stdy.studysearch.ApiHttpResponse
import java.util.Optional

internal fun <T> T?.or404(): Any = this ?: ApiHttpResponse.RESOURCE_NOT_FOUND

internal fun <T> Optional<T>.or404(): Any = if (this.isPresent) this.get() else ApiHttpResponse.RESOURCE_NOT_FOUND