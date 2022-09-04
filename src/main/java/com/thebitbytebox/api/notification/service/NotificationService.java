package com.thebitbytebox.api.notification.service;

public interface NotificationService<R> {
      boolean publish(R request);
}
