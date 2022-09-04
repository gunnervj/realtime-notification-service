package com.thebitbytebox.api.notification.controller;

import com.thebitbytebox.api.notification.kafka.dto.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationTest {
    private final StreamBridge streamBridge;

    @PostMapping("/notifications/test")
    public void publishNotificationRequest(@RequestBody NotificationEvent notificationRequest) {
        streamBridge.send("testNotificationProducer-out-0", notificationRequest);
    }
}
