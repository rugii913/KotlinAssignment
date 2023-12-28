package kotlinassignment.week4.domain.exception

import kotlinassignment.week4.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

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
}