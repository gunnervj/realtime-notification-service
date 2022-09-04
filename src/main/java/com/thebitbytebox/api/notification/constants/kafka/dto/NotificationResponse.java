package com.thebitbytebox.api.notification.constants.kafka.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationResponse {
    private String message;
    private String userId;
    private Boolean status;
}
