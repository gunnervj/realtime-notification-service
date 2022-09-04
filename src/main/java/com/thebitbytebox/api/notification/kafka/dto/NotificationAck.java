package com.thebitbytebox.api.notification.kafka.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationAck {
    private String message;
    private String userId;
    private Boolean status;
}
