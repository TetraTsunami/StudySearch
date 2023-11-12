package tech.stdy.studysearch

import org.springframework.http.HttpStatus

data class ApiErrorResponse(val code: Int, val message: String) {
    companion object {
        val RESOURCE_NOT_FOUND = ApiErrorResponse(HttpStatus.NOT_FOUND.value(), "Resource not found")
    }
}
