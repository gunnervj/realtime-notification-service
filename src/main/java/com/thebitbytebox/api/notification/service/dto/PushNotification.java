package com.thebitbytebox.api.notification.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class PushNotification {
    private String message;
    private String userId;
}
