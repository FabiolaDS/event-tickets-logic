package com.eventtickets.logictier.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class Category {
	private Long id;
	@NotBlank
	@Size(min = 5, max = 20, message = "The category should be between 5 and 20 character long")
	private String name;
}
