package com.fabri.srvnotification.application.io;

import java.time.Instant;

public record EmailSendOutput(String id,
                              String to,
                              String usecase,
                              String fromSrv,
                              Instant sentAt) {

    public EmailSendOutput(String id, String to, String usecase, String fromSrv) {
        this(id, to, usecase, fromSrv, Instant.now());
    }
}
