package com.eventtickets.logictier.service;

import com.eventtickets.logictier.model.Notification;

import java.util.List;

public interface NotificationService {
	Notification createNotification(Notification notification);
	List<Notification> getNotificationsByUser(long userId);
	Notification notify(Notification notification, List<Long> users);
}
