package com.thebitbytebox.api.notification.service;

import com.thebitbytebox.api.notification.constants.NotificationConstants;
import com.thebitbytebox.api.notification.service.dto.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientRegistrationService implements RegistrationService {
    private final RedissonClient redissonClient;

    @Override
    public void register(Client client) {
        RMap<String, Client> clients = redissonClient.getMap(NotificationConstants.CLIENTS_MAP_NAME);
        clients.put(client.getUserId(), client);
    }

    @Override
    public void deregister(Client client) {
        RMap<String, Client> clients = redissonClient.getMap(NotificationConstants.CLIENTS_MAP_NAME);
        clients.remove(client.getUserId());
    }
}
