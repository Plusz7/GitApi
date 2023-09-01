package com.practice.gitapi.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(NotAcceptableException::class)
    fun handleNotAcceptableException(
        ex: NotAcceptableException,
        request: WebRequest
    ): ResponseEntity<Any> {
        val body = mapOf(
            "status" to HttpStatus.NOT_ACCEPTABLE.value(),
            "message" to "The requested media type is not supported."
        )
        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        return ResponseEntity(body, headers, HttpStatus.NOT_ACCEPTABLE)
    }


}

class NotAcceptableException: RuntimeException()