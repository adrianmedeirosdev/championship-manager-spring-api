package com.championship.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationErrors {
  private LocalDateTime dateTime;
  private String title;
  private List<Error> errors = new ArrayList<>();

    public ValidationErrors(LocalDateTime dateTime, String title) {
      this.dateTime = dateTime;
      this.title = title;
    }

    public void addError(Error error){
      errors.add(error);
    }


}
