package com.fabri.srvmessagebroker.domain.vo;

import java.util.ArrayList;
import java.util.List;

public record EmailNotificationQueue(
        String to,
        List<String> cc,
        String title,
        String body
) {
    public EmailNotificationQueue(String to, String title, String body) {
        this(to, new ArrayList<>(), title, body);
    }

}
