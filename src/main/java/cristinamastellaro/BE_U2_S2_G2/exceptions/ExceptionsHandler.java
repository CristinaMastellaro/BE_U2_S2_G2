package cristinamastellaro.BE_U2_S2_G2.exceptions;

import cristinamastellaro.BE_U2_S2_G2.payloads.ErrorsPayload;
import cristinamastellaro.BE_U2_S2_G2.payloads.ErrorsValidationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(AuthorAlreadyWrittenBlogException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleAuthorAlreadyWritten(AuthorAlreadyWrittenBlogException e) {
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleEmailAlreadyUsed(EmailAlreadyUsedException e) {
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayload handleIdNotFound(IdNotFoundException e) {
        return new ErrorsPayload(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ValidationBodyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsValidationDTO handleValidationException(ValidationBodyException e) {
        return new ErrorsValidationDTO(e.getMessage(), LocalDateTime.now(), e.getErrorsList());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGenericError(Exception e) {
        e.getStackTrace();
        return new ErrorsPayload("Ci sono stati errori nel back-end che risolveremo il prima possibile", LocalDateTime.now());
    }
}
