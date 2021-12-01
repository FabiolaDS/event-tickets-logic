package com.eventtickets.logictier.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Notification {
	private Long id;
	@NonNull
	private String title;
	@NonNull
	private String message;
	@NonNull
	private LocalDateTime timestamp;

}
