package com.fabri.srvnotification.infra.adapters.controller;

public record EmailRequest(String email,
                           String title,
                           String message) {
}
