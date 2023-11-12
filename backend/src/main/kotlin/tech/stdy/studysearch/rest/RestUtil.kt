package tech.stdy.studysearch.rest

import tech.stdy.studysearch.ApiErrorResponse
import java.util.Optional

internal fun <T> Optional<T>.or404(): Any = if (this.isPresent) this.get() else ApiErrorResponse.RESOURCE_NOT_FOUND