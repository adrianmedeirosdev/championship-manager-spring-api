package com.championship.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
  
  @Autowired
  private MessageSource messageSource;
  
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ValidationErrors handle(MethodArgumentNotValidException exception){
    
    String title = "Um ou mais campos estão inválidos.";
    ValidationErrors errors = new ValidationErrors(LocalDateTime.now(), title);

    List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

    fieldErrors.forEach(field -> {
      String message = messageSource.getMessage(field, LocaleContextHolder.getLocale());

      errors.addError(new Error(field.getField(), message));
    });

    return errors;
  }

}
