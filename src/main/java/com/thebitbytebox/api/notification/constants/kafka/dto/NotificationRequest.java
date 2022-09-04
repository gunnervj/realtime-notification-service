package com.thebitbytebox.api.notification.constants.kafka.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String userId;
    private NotificationType notificationType;
    private String message;
}
