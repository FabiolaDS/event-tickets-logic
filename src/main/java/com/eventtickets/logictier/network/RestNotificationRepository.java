package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.CreditCard;
import com.eventtickets.logictier.model.Notification;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestNotificationRepository extends RestRepository
	implements NotificationRepository {
	public RestNotificationRepository(
		@NonNull RestTemplate restTemplate,
		@Value("${eventTicket.data.url}") String dataUrl) {
		super(restTemplate, dataUrl, "/notifications");
	}

	@Override
	public Notification createNotification(Notification notification) {
		return rest().postForObject(url(), notification, Notification.class);
	}

	@Override
	public List<Notification> getNotificationsByUser(long userId) {
		ResponseEntity<List<Notification>> response = rest()
			.exchange(url("byUser", userId),
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Notification>>() {
				});

		return response.getBody();
	}

	@Override
	public Notification addNotification(long notificationId, long userId) {
		return rest().postForObject(
			url("byUser/{userId}?notificationId={notificationId}"), null,
			Notification.class, userId, notificationId);
	}
}
