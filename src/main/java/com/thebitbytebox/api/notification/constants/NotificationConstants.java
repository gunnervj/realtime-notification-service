package com.thebitbytebox.api.notification.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NotificationConstants {
    public static final String CLIENTS_MAP_NAME = "notification-clients";
    public static final String USER_MESSAGE_DESTINATION = "/queue/notify";
}
