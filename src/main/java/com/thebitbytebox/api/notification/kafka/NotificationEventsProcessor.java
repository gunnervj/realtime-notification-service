package com.thebitbytebox.api.notification.kafka;

import com.thebitbytebox.api.notification.kafka.dto.NotificationEvent;
import com.thebitbytebox.api.notification.kafka.dto.NotificationAck;
import com.thebitbytebox.api.notification.service.NotificationService;
import com.thebitbytebox.api.notification.service.dto.PushNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("notificationRequestProcessor")
@RequiredArgsConstructor
@Slf4j
public class NotificationEventsProcessor implements Function<NotificationEvent, NotificationAck> {
    private final NotificationService<PushNotification> notificationService;

    @Override
    public NotificationAck apply(NotificationEvent notificationEvent) {
        PushNotification pushNotification = PushNotification.builder()
                .message(notificationEvent.getMessage())
                .userId(notificationEvent.getUserId())
                .build();
        boolean status = notificationService.publish(pushNotification);
        return NotificationAck.builder()
                .message(notificationEvent.getMessage())
                .userId(notificationEvent.getUserId())
                .status(status)
                .build();
    }
}
