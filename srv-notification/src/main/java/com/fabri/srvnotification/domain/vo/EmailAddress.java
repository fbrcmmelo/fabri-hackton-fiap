package com.fabri.srvnotification.domain.vo;

import com.fabri.srvnotification.infra.utils.MailValidatorUtils;

public record EmailAddress(String address) {

    public EmailAddress {
        MailValidatorUtils.validate(address);
    }
}
