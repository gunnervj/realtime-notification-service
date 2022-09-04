package com.thebitbytebox.api.notification.service;

import com.thebitbytebox.api.notification.service.dto.Client;

public interface RegistrationService {
    void register(Client client);
    void deregister(Client client);
}
