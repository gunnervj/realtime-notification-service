package com.thebitbytebox.api.notification.kafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEvent {
    private String userId;
    private NotificationType notificationType;
    private String message;
}
