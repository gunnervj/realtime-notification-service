package com.thebitbytebox.api.notification.service;

import com.thebitbytebox.api.notification.constants.NotificationConstants;
import com.thebitbytebox.api.notification.service.dto.Client;
import com.thebitbytebox.api.notification.service.dto.PushNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PushNotificationService implements NotificationService<PushNotification>{
    private final RedissonClient redissonClient;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public boolean publish(PushNotification pushNotification) {
        try {
            RMap<String, Client> clients =  redissonClient.getMap(NotificationConstants.CLIENTS_MAP_NAME);
            Client client = clients.get(pushNotification.getUserId());
            if (null == client) {
                log.warn("User Not found for ID: {}", pushNotification.getUserId());
            } else {
                messagingTemplate.convertAndSendToUser(client.getSessionId(),
                        NotificationConstants.USER_MESSAGE_DESTINATION,
                        pushNotification.getMessage(),
                        createHeaders(client.getSessionId()));
                return Boolean.TRUE;
            }
        } catch (MessagingException e) {
            log.error("Error while sending push notification for {}: Error :: {}",
                    pushNotification.getUserId(),
                    e.getMessage(), e);
        }
        return Boolean.FALSE;
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor
                .create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(Boolean.TRUE);
        return headerAccessor.getMessageHeaders();
    }
}
