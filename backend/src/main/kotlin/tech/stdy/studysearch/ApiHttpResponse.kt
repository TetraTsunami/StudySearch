package tech.stdy.studysearch

import org.springframework.http.HttpStatus

data class ApiHttpResponse(val code: Int, val message: String) {
    companion object {
        val RESOURCE_NOT_FOUND = ApiHttpResponse(HttpStatus.NOT_FOUND.value(), "Resource not found")
        val ENDPOINT_NOT_FOUND = ApiHttpResponse(HttpStatus.NOT_FOUND.value(), "Endpoint not found")
        val SUCCESS = ApiHttpResponse(HttpStatus.OK.value(), "Success")
    }
}
