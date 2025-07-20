package com.fabri.srvnotification.application.io;

import java.util.List;

public record EmailInput(String to, List<String> cc, String title, String message) {
}
