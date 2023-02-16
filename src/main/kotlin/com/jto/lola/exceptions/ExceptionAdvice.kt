package com.jto.lola.exceptions

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionAdvice {
    @ExceptionHandler(
        value = [//NotFoundException::class, AlreadyExistException::class, FunctionException::class,
            IllegalAccessException::class, AccessDeniedException::class, Exception::class]
    )
    fun exceptionHandler(ex: Exception): ResponseEntity<ErrorMessage> {
        logger.error("Reporting occurred exception occur", ex)
        val status: HttpStatus = when (ex) {
            //is NotFoundException -> HttpStatus.NOT_FOUND
            //is AlreadyExistException -> HttpStatus.CONFLICT //EXISTING RESOURCE
            //is FunctionException -> HttpStatus.INTERNAL_SERVER_ERROR
            is IllegalAccessException -> HttpStatus.FORBIDDEN
            is AccessDeniedException -> HttpStatus.FORBIDDEN
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        val exceptionMessage = ex.message ?: ex.toString()
        val exceptionCauseMessage = ex.cause?.message
        val exceptionClass = ex.javaClass.canonicalName
        when (ex) {
            is IErrorCodeText -> return ResponseEntity<ErrorMessage>(
                ServerErrorMessage(
                    httpStatus = status,
                    message = exceptionMessage,
                    causeMessage = exceptionCauseMessage,
                    exceptionClass = exceptionClass,
                    errorCode = ex.errorCode,
                    errorCodeText = ex.errorCodeText,
                ), status
            )
            else ->  return ResponseEntity<ErrorMessage>(
                ErrorMessage(
                    httpStatus = status,
                    message = exceptionMessage,
                    causeMessage = exceptionCauseMessage,
                    exceptionClass = exceptionClass,
                ), status
            )
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExceptionAdvice::class.java)
    }
}