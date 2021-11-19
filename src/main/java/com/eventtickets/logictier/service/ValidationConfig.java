package com.eventtickets.logictier.service;

import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationConfig
{
  @Bean
  public Validator provideValidator()
  {
    return Validation.buildDefaultValidatorFactory().getValidator();
  }
}
