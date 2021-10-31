package src.configs.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerAdvice(private val messageSource: MessageSource) {

    /**
     * Handle for application's errors
     * return HttpStatus 500 - INTERNAL SERVER ERROR
     * */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleServerErrorException(
        exception: Exception,
        request: HttpServletRequest
    ) = ExceptionResponse(
        status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        error = HttpStatus.INTERNAL_SERVER_ERROR.name,
        field = "",
        message = exception.message!!,
        path = request.servletPath
    )

    /**
     * Handle for validations errors
     * return HttpStatus 400 - BAD REQUEST
     * */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ExceptionValidationResponse {

        val fieldsErrorMap = HashMap<String, String>()

        exception.bindingResult.fieldErrors.forEach { fieldError ->
            val message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale())

            fieldsErrorMap.put(fieldError.field, message)
        }

        return ExceptionValidationResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            fields = fieldsErrorMap,
            message = "Validation Error",
            path = request.servletPath
        )
    }

    /**
     * Handle for User Not Found
     * return HttpStatus 404 - NOT FOUND
     * */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundExceptionException(
        exception: Exception,
        request: HttpServletRequest
    ) = ExceptionResponse(
        status = HttpStatus.NOT_FOUND.value(),
        error = HttpStatus.NOT_FOUND.name,
        field = "",
        message = exception.message!!,
        path = request.servletPath
    )

}

/**
 * Exception Response for all application
 * */
data class ExceptionResponse(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val field: String,
    val message: String,
    val path: String
)

/**
 * Specific Exception Response for validations errors
 * */
data class ExceptionValidationResponse(
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val fields: Map<String, String>,
    val path: String
)