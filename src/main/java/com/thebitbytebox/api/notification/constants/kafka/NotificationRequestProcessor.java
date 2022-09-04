package com.thebitbytebox.api.notification.constants.kafka;

import com.thebitbytebox.api.notification.constants.kafka.dto.NotificationRequest;
import com.thebitbytebox.api.notification.constants.kafka.dto.NotificationResponse;
import com.thebitbytebox.api.notification.service.NotificationService;
import com.thebitbytebox.api.notification.service.dto.PushNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("notificationRequestProcessor")
@RequiredArgsConstructor
@Slf4j
public class NotificationRequestProcessor implements Function<NotificationRequest, NotificationResponse> {
    private final NotificationService<PushNotification> notificationService;

    @Override
    public NotificationResponse apply(NotificationRequest notificationRequest) {
        PushNotification pushNotification = PushNotification.builder()
                .message(notificationRequest.getMessage())
                .userId(notificationRequest.getUserId())
                .build();
        boolean status = notificationService.publish(pushNotification);
        return NotificationResponse.builder()
                .message(notificationRequest.getMessage())
                .userId(notificationRequest.getUserId())
                .status(status)
                .build();
    }
}
