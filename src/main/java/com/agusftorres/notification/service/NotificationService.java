package com.agusftorres.notification.service;

public interface NotificationService {
   void send(String type, String userId, String message);
}
