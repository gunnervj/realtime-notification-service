package com.thebitbytebox.api.notification.controller;

import com.thebitbytebox.api.notification.service.RegistrationService;
import com.thebitbytebox.api.notification.service.dto.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {
    private final RegistrationService registrationService;

    @MessageMapping("/register")
    public void start(@Payload Client client, StompHeaderAccessor stompHeaderAccessor) {
        log.info("Client Registration Request Received : {}", client.getUserId());
        registrationService.register(client);
    }

    @MessageMapping("/deregister")
    public void remove(@Payload Client client, StompHeaderAccessor stompHeaderAccessor) {
        log.info("Client De-Registration Request Received : {}", client.getUserId());
        registrationService.deregister(client);
    }
}
