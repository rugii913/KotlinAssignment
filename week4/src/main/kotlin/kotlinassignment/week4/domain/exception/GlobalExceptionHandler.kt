package kotlinassignment.week4.domain.exception

import jakarta.validation.ConstraintViolationException
import kotlinassignment.week4.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = "요청의 body 중 형식이 적절하지 않은 데이터가 입력되었습니다."))
        // POST /todocards 요청이 들어갈 때
        // body의 createdDateTime에 LocalDateTime 형식이 아닌 데이터(ex.단순 String) 입력 시도 때문에 추가한 exception handler
        // 이 exception handler가 없더라도 서버에서 알아서 400을 보내주지만, 형식을 통일하기 위한 것
        // 참고: https://wecandev.tistory.com/15
    }

    @ExceptionHandler(BindException::class)
    fun handleBindException(e: BindException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            //.body(ErrorResponse(message = e.message))
            .body(ErrorResponse(message = e.bindingResult.allErrors[0].defaultMessage))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleBindException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = "요청의 path variable 중 형식이 적절하지 않은 데이터가 입력되었습니다."))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleBindException(e: ConstraintViolationException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(PasswordMismatchException::class)
    fun handlePasswordMismatchException(e: PasswordMismatchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(IncorrectRelatedEntityIdException::class)
    fun handlePasswordMismatchException(e: IncorrectRelatedEntityIdException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = e.message))
    }

    /*
    // ToDoCardControllre mapping url에 억지로 "/"을 유지하면서, null이 들어올 경우 이렇게 처리하려 했음
    // 하지만 "/"를 아예 빼기로 결정함
    @ExceptionHandler(MissingPathVariableException::class)
    fun handleBindException(e: MissingPathVariableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(message = "요청의 path variable(${e.variableName})은 필수값입니다."))
    }
     */
}