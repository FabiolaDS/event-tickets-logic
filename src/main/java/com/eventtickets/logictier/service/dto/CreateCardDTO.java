package com.eventtickets.logictier.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CreateCardDTO
{
  @CreditCardNumber
  private String cardNumber;
  @Min(value = 1, message = "Expiry month should be higher than 1")
  @Max(value = 12, message = "Expiry month should not be higher than 12")
  private int expiryMonth;
  @Min(2000)
  private int expiryYear;
  @Min(value = 0, message = "Invalid CVV")
  @Max(value = 999, message = "Invalid CVV")
  private int cvv;
  @NotBlank
  @Size(min = 5, max = 150, message = "The name should be between 5 and 150 character long")
  private String cardOwnerName;
  private long ownerId;
}
