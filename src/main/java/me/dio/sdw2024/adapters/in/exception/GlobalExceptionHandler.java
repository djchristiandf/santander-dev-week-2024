package me.dio.sdw2024.adapters.in.exception;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.dio.sdw2024.domain.exception.ChampionNotfoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
  /*****************************************************************************
   * Logger
   * 
   */
  private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ChampionNotfoundException.class)
  public ResponseEntity<ApiError> handleDomainException(ChampionNotfoundException domainError) {
    return ResponseEntity
        .unprocessableEntity()
        .body(new ApiError(domainError.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
    String message = "Ops! Ocorreu um erro inesperado!";
    logger.error(message, unexpectedError);
    return ResponseEntity
        .internalServerError()
        .body(new ApiError(message));
  }

  /*******************************************************************************
   * GLOBAL EXCEPTION HANDLER *
   *******************************************************************************/

  /**
   * Handles exceptions that are not caught by specific handlers. This method is
   * called when no other handler has been found for a given exception.
   * Method that handles exceptions of type {@link ResponseEntityExceptionHandler}
   * and converts them into a standardized response (HTTP status code + message).
   * 
   * @param e Exception to be handled
   * @return A {@code ResponseEntity} with the HTTP status code and error message
   */
  public record ApiError(String message) {
  }
}
