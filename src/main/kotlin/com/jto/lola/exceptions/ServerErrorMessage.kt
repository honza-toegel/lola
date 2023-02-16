package com.jto.lola.exceptions

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ServerErrorMessage(
    timestamp: LocalDateTime = LocalDateTime.now(),
    httpStatus: HttpStatus,
    status: Int = httpStatus.value(),
    error: String = httpStatus.reasonPhrase,
    message: String,
    causeMessage: String?,
    exceptionClass: String,
    val errorCode: String,
    val errorCodeText: String
) : ErrorMessage(timestamp, httpStatus, status, error, message, causeMessage, exceptionClass)