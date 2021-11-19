package com.eventtickets.logictier.service;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends RuntimeException
{
  private Set<ConstraintViolation<? super Object>> violations;

  public ValidationException(Set<ConstraintViolation<? super Object>> violations)
  {
    this.violations = violations;
  }
}
