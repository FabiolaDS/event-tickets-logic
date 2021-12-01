package com.eventtickets.logictier.network;

import com.eventtickets.logictier.model.Notification;

import java.util.List;

public interface NotificationRepository {
	Notification createNotification(Notification notification);
	List<Notification> getNotificationsByUser(long userId);
	Notification addNotification(long notificationId, long userId);
}
