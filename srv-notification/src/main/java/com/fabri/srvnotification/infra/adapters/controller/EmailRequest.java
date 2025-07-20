package com.fabri.srvnotification.infra.adapters.controller;

import java.util.List;

public record EmailRequest(
        String to,
        List<String> cc,
        String title,
        String body) {
}
