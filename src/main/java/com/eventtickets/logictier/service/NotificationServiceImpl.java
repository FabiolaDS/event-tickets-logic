package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Notification;
import com.eventtickets.logictier.network.NotificationRepository;
import com.eventtickets.logictier.service.dto.CreateCardDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	@NonNull
	private Validator validator;

	@NonNull
	private NotificationRepository notificationRepository;

	@Override
	public Notification createNotification(Notification notification) {
		Set<ConstraintViolation<Notification>> violations = validator
			.validate(notification);
		for (ConstraintViolation<Notification> violation : violations) {
			throw new IllegalArgumentException(violation.getMessage());
		}

		return notificationRepository.createNotification(notification);
	}

	@Override
	public List<Notification> getNotificationsByUser(long userId) {
		return notificationRepository.getNotificationsByUser(userId);
	}

	@Override
	public Notification notify(Notification notification, List<Long> users) {
		Notification saved = notificationRepository
			.createNotification(notification);
		for (long id : users) {
			notificationRepository.addNotification(saved.getId(), id);
		}
		return saved;
	}
}
